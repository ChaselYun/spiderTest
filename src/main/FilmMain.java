package main;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import db.MYSQLControl;
import model.FilmModel;
import util.URLFecter;

public class FilmMain {


    static final Log logger = LogFactory.getLog(FilmMain.class);
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url="http://58921.com/";
        //抓取的数据
        List<FilmModel> filmModel=URLFecter.URLParser(client, url);
        //循环输出抓取的数据
        for (FilmModel f:filmModel) {
            logger.info("filmName:"+f.getFilmName()+"\t"+"schedule:"+f.getSchedule()+"\t"+"boxOffice:"+f.getBoxOffice());
        }
        //将抓取的数据插入数据库
        MYSQLControl.executeInsert(filmModel);
    }

}
