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
              String sourceFilePath = "D:\\upload\\hotel_comments.csv"; //源文件
              //容器：对象少的时候，直接把对象列出来；当对象很多的时候，要用一个容器装起来打包
              ArrayList<String> csvFileList = new ArrayList<String>();  
              // 这个不用背，只要看得懂会用就行。创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);  
              CsvReader reader = new CsvReader(sourceFilePath, ',', Charset.forName("utf-8"));  
              // 跳过表头 如果需要表头的话，这句可以忽略  
              reader.readHeaders(); 
              // 逐行读入除表头的数据  
              reader.getValues();
              //boolean变量：真假true或者false
              while (reader.readRecord()) {  
            	 //System.out.println(reader.getRawRecord()); 
                  //将一行的字符串按照“，”逗号分成多列，存放到String[]数组中
                  //再将这个string[]放到list容器中存起来
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
            //数据取完了，关闭文件
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
