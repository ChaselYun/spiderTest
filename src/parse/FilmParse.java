package parse;

import java.util.ArrayList;
import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.FilmModel;

public class FilmParse {
	public static List<FilmModel> getData (String html) throws Exception{
        //��ȡ�����ݣ�����ڼ�����
        List<FilmModel> data = new ArrayList<FilmModel>();
        //����Jsoup����
        Document doc = Jsoup.parse(html);
        //��ȡhtml��ǩ�е�����
        Elements elements= doc.select("tbody>tr");
        //Elements elements= doc.select("tbody:nth-child(2)>tr");
        for (Element ele:elements) {
//        	Elements es = ele.select("td");
//        	for(Element e : es{
//        		String filmName = e
//        	})
            String filmName=ele.select("td:nth-child(1)>a").text();
            String schedule=ele.select("td:nth-child(2)").text();
            String boxOffice=ele.select("td:nth-child(5)").text();
            if(filmName.equals("") || schedule.equals("") || boxOffice.equals("") || boxOffice.equals("--")){
            	continue;
            }
            //����һ������������Կ�����ʹ��Model�����ƣ�ֱ�ӽ��з�װ
            FilmModel filmModel=new FilmModel();
            //�����ֵ
            filmModel.setFilmName(filmName);;
            filmModel.setSchedule(schedule);;
            filmModel.setBoxOffice(boxOffice);;
            //��ÿһ�������ֵ�����浽List������
            data.add(filmModel);
        }
        //��������
        return data;
    }
}
