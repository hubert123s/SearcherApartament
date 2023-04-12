package com.example.searcherapartament.scraper;

import com.example.searcherapartament.helper.DocumentHelper;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class Olx {
    private final DocumentHelper documentHelper;
    private final static int DIFFERENCETOREMOVE = 13;
    private final static String CSSQUERYBASICCOST = "h3.css-ddweki.er34gjf0";
   // css-ddweki er34gjf0
    //cityNormalizedName\":\"wroclaw\",\"regionName\":\"Dolnośląskie\",
    // \"regionId\":3,\"regionNormalizedName\":\"dolnoslaskie\",\"districtName\"
    // :\"Psie Pole\",\"districtId\":389,\"pathName\":\"Dolnośląskie, Wrocław, Psie Pole\"},

    //czy prywatne czy firmowe
    // <li class="css-ox1ptj"><style data-emotion-css="xl6fe0-Text">.css-xl6fe0-Text{font-size:14px;line-height:18px;color:#002F34;margin:0;}</style><p class="css-xl6fe0-Text eu5v0x0"><span>Prywatne</span></p></li>

    //cena regularna
    //         <h3 class="css-okktvh-Text eu5v0x0">867<!-- --> <!-- -->zł</h3>
    //         <h3 class="css-okktvh-Text eu5v0x0">700<!-- --> <!-- -->zł</h3>
    public Long getBasicCost(String url)
    {
        Document document =documentHelper.getDocument(url);
        return Long.valueOf(document.select(CSSQUERYBASICCOST).text().replace("zł","").replace(" ","").trim());
    }
    public Long getAdditionalCost(String url)
    {
        Document document =documentHelper.getDocument(url);
        Elements rentAdditionally = document.select("li.css-ox1ptj");
        List<String> rents = rentAdditionally.eachText();
        for(String rent:rents)
        {
            if(rent.contains("Czynsz (dodatkowo):"))
            {
                String rentValue = rent
                        .replace("Czynsz (dodatkowo):", "")
                        .replace("zł", "")
                        .replace(" ", "");
                try{
                    return   Long.parseLong(rentValue);

                }catch (NumberFormatException exception)
                {
                    exception.getMessage();
                    if(Double.parseDouble(rentValue.replace(",","."))<1.00)
                    {
                        return 0L;
                    }
                    return 0L;
                }
            }
        }
        return 0L;
    }
   public String getLocation(String url)
    {
        String document =documentHelper.getDocument(url).toString();
        int index = document.indexOf("pathName");
        StringBuilder newDocument = new StringBuilder(document).delete(0,index+DIFFERENCETOREMOVE);
        String splitDocument = newDocument.toString().split("\"")[0];
        return splitDocument.substring(0,splitDocument.length()-1);
    }
    public boolean isActiveOffer(String url)
    {
        Document document = documentHelper.getDocument(url);
        String divClass= "h6.css-1tcorc4-Text.eu5v0x0";
        return document.select(divClass).text().contains("To ogłoszenie nie jest już dostępne")?false:true;
    }
//    public boolean isFewViewsOffer(String url, int fewViews){
//        Document document = documentHelper.getDocument(url);
//        String divClass= "span.css-42xwsi";
//        //System.out.println(document.toString());
//        System.out.println("views"+document.select(divClass).text());
//        System.out.println("views"+document.select("span.css-12hdxwj"));
//        System.out.println("views"+document.select("span.css-19yf5ek"));
//        return false;
//    }
    public String getImageLink(String url){
        String divClass =".swiper-zoom-container";
        Document document =documentHelper.getDocument(url);
       // System.out.println("koncowe "+document.select(".css-1bmvjcs").first().attr("src")); // inna rozdzielczosc jest w srcset
        try {
            return document.select(".css-1bmvjcs").first().attr("src");
        }catch (NullPointerException exception){
            return " ";
        }
    }
}
