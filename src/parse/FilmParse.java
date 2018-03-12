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
        //获取的数据，存放在集合中
        List<FilmModel> data = new ArrayList<FilmModel>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
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
            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            FilmModel filmModel=new FilmModel();
            //对象的值
            filmModel.setFilmName(filmName);;
            filmModel.setSchedule(schedule);;
            filmModel.setBoxOffice(boxOffice);;
            //将每一个对象的值，保存到List集合中
            data.add(filmModel);
        }
        //返回数据
        return data;
    }
}
