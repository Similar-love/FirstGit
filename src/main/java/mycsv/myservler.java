package mycsv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csvreader.CsvReader;
import com.google.gson.Gson;

@WebServlet("/myservler")
public class myservler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Integer> map=new HashMap<String,Integer>();
              String sourceFilePath = "D:\\upload\\hotel_comments.csv"; //Դ�ļ�
              //�����������ٵ�ʱ��ֱ�ӰѶ����г�����������ܶ��ʱ��Ҫ��һ������װ�������
              ArrayList<String> csvFileList = new ArrayList<String>();  
              // ������ñ���ֻҪ���ö����þ��С�����CSV������ ����:CsvReader(�ļ�·�����ָ����������ʽ);  
              CsvReader reader = new CsvReader(sourceFilePath, ',', Charset.forName("utf-8"));  
              // ������ͷ �����Ҫ��ͷ�Ļ��������Ժ���  
              reader.readHeaders(); 
              // ���ж������ͷ������  
              reader.getValues();
              //boolean���������true����false
              while (reader.readRecord()) {  
            	 //System.out.println(reader.getRawRecord()); 
                  //��һ�е��ַ������ա��������ŷֳɶ��У���ŵ�String[]������
                  //�ٽ����string[]�ŵ�list�����д�����
                  csvFileList.add(reader.get(0)); 
                 // System.out.println(reader.get(0)); 
//                  System.out.print(reader.get(1)); 
//                  System.out.print(reader.get(2)); 
//                  System.out.println(reader.get(3)); 
              }
              for(String str:csvFileList) {
            	 
            		  if(map.containsKey(str)){
                          map.put(str, map.get(str) + 1);
                      }else{
                          map.put(str, 1);
                      }
            	  
              }
//              Iterator it = map.entrySet().iterator();
//              while(it.hasNext()){
//                  Entry ex = (Entry) it.next();
//                  System.out.println(ex.getKey() + ":" + ex.getValue());
//              }
            //����ȡ���ˣ��ر��ļ�
              reader.close(); 
		 
			Gson g=new Gson();
			String jsonStr = g.toJson(map);
			//System.out.println(jsonStr.toString());
			response.getWriter().print(jsonStr);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
