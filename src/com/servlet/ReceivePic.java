package com.servlet;
import java.io.File;

import java.io.IOException;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ReceivePic extends HttpServlet {  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        request.setCharacterEncoding("utf-8");  
        //��ô����ļ���Ŀ������  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //��ȡ�ļ��ϴ���Ҫ�����·����upload�ļ�������ڡ�  
        String path = request.getSession().getServletContext().getRealPath("/")+"uploadImage";  
        System.out.println(path);
        //������ʱ����ļ��Ĵ洢�ң�����洢�ҿ��Ժ����մ洢�ļ����ļ��в�ͬ����Ϊ���ļ��ܴ�Ļ���ռ�ù����ڴ��������ô洢�ҡ�  
        factory.setRepository(new File(path));  
        //���û���Ĵ�С�����ϴ��ļ���������������ʱ���ͷŵ���ʱ�洢�ҡ�  
        factory.setSizeThreshold(1024*1024);  
        //�ϴ����������ࣨ��ˮƽAPI�ϴ���������  
        ServletFileUpload upload = new ServletFileUpload(factory);  
          
          
            //���� parseRequest��request������  ����ϴ��ļ� FileItem �ļ���list ��ʵ�ֶ��ļ��ϴ���  
            List<FileItem> list = null;
			try {
				list = (List<FileItem>)upload.parseRequest(request);
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
            for(FileItem item:list){  
                //��ȡ�����������֡�  
                String name = item.getFieldName();  
                //�����ȡ�ı�����Ϣ����ͨ���ı���Ϣ����ͨ��ҳ�������ʽ���������ַ�����  
                if(item.isFormField()){  
                    //��ȡ�û�����������ַ�����  
                    String value = item.getString();  
                    request.setAttribute(name, value);  
                }  
                //���������ǷǼ��ַ���������ͼƬ����Ƶ����Ƶ�ȶ������ļ���  
                else{   
                    //��ȡ·����  
                    String value = item.getName();  
                    //ȡ�����һ����б�ܡ�  
                    int start = value.lastIndexOf("\\");  
                    //��ȡ�ϴ��ļ��� �ַ������֡�+1��ȥ����б�ܡ�  
                    String filename = value.substring(start+1);  
                    request.setAttribute(name, filename);  
                      
                     try {
						item.write(new File(path,filename));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    //�յ�д�����յ��ļ��С� 
//                    File file = new File(path,filename);
//                   
//                    OutputStream out = new FileOutputStream(file);  
//                    InputStream in = item.getInputStream();  
//                      
//                    int length = 0;  
//                    byte[] buf = new byte[1024];  
//                    System.out.println("��ȡ�ļ�����������:"+ item.getSize());  
//                      
//                    while((length = in.read(buf))!=-1){  
//                        out.write(buf,0,length);  
//                    }  
//                    in.close();  
//                    out.close();  
                }  
            }  
        
          
    }  
}  