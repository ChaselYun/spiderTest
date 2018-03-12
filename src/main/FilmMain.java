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
        //��ʼ��һ��httpclient
        HttpClient client = new DefaultHttpClient();
        //����Ҫ��ȡ��һ����ַ��������Դ����ݿ��г�ȡ���ݣ�Ȼ������ѭ����������ȡһ��URL����
        String url="http://58921.com/";
        //ץȡ������
        List<FilmModel> filmModel=URLFecter.URLParser(client, url);
        //ѭ�����ץȡ������
        for (FilmModel f:filmModel) {
            logger.info("filmName:"+f.getFilmName()+"\t"+"schedule:"+f.getSchedule()+"\t"+"boxOffice:"+f.getBoxOffice());
        }
        //��ץȡ�����ݲ������ݿ�
        MYSQLControl.executeInsert(filmModel);
    }

}
