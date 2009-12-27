package subs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.htmlparser.Parser;
import org.htmlparser.http.ConnectionManager;
import org.htmlparser.http.ConnectionMonitor;
import org.htmlparser.http.HttpHeader;
import org.htmlparser.util.ParserException;

class Login extends JFrame implements ActionListener
{
    public static final String baseUrl = "http://sratim.co.il";
    private static final String SET_COOKIE = "Set-Cookie";
    private static final String COOKIE_VALUE_DELIMITER = ";";
    private static final char NAME_VALUE_SEPARATOR = '=';
    private static final char DOT = '.';
    
    static ConnectionManager _manager;

    JButton                    SUBMIT;
    JPanel                     panel;
    JLabel                     label1, label2;
    final JTextField           text1, text2, capatchText;
    JButton                    btn;
    JPanel                     capatcha;

    Login()
    {
        label1 = new JLabel();
        label1.setText("Username:");
        text1 = new JTextField("crouprat", 15);

        label2 = new JLabel();
        label2.setText("Password:");
        text2 = new JTextField("Y5TfTT5W", 15);
        capatchText = new JTextField("", 15);
        btn = new JButton();
        btn.setSize(100, 100);
        SUBMIT = new JButton("SUBMIT");

        panel = new JPanel(new GridLayout(5, 1));
        panel.add(label1);
        panel.add(text1);
        panel.add(label2);
        panel.add(text2);
        panel.add(new Label("capatcha"));
        panel.add(capatchText);
        panel.add(btn);
        panel.add(new Label());
        panel.add(SUBMIT);
        add(panel, BorderLayout.CENTER);
        SUBMIT.addActionListener(this);
        setTitle("LOGIN FORM");
        getImage();
//        getImage();
    }

    private void getImage()
    {
        try
        {
            _manager = Parser.getConnectionManager();
            ConnectionMonitor monitor = new ConnectionMonitor()
            {
                public void preConnect(HttpURLConnection connection)
                {
                    System.out.println(HttpHeader.getRequestHeader(connection));
                }

                public void postConnect(HttpURLConnection connection)
                {
                    System.out
                            .println(HttpHeader.getResponseHeader(connection));
                }
            };
            _manager.setMonitor(monitor);
            _manager.setCookieProcessingEnabled(true);
            // perform the connection
            Parser parser = new Parser(baseUrl + "/users/login.aspx");
            parser.setEncoding("UTF-8");
            parser = new Parser(baseUrl + "/verificationimage.aspx");
            URLConnection con = parser.getConnection();
            ImageIcon icon = new ImageIcon(ImageIO.read(con.getInputStream()));
            btn.setIcon(icon);
        }
        catch (ParserException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static HttpURLConnection createPost(String urlString,
            StringBuffer extraProps)
    {
        URL url;
        HttpURLConnection connection = null;
        PrintWriter out;

        try
        {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            _manager.addCookies(connection);

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);

            // more or less of these may be required
            // see Request Header Definitions:
            // http://www.ietf.org/rfc/rfc2616.txt
            connection.setRequestProperty("Accept-Charset", "*");
            connection.setRequestProperty("Accept_Languaget", "en-us,en;q=0.5");
            connection.setRequestProperty("Accept-Encoding", "gzip,deflate");
            connection.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
            connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            connection.setRequestProperty("Referer", "www.torec.net");

            out = new PrintWriter(connection.getOutputStream());
            out.print(extraProps);
            out.close();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        return connection;
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String value1 = text1.getText();
        String value2 = text2.getText();
        StringBuffer sb = new StringBuffer();
        sb.append("Username=");
        sb.append(value1);
        sb.append("&Password=");
        sb.append(value2);
        sb.append("&VerificationCode=");
        sb.append(capatchText.getText());
        // sb.append("&Referrer=%2Fdefault.aspx%3F");
        sb.append("&Referrer=www.sratim.co.il");
        HttpURLConnection connection = createPost(baseUrl
                + "/users/login.aspx", sb);
        Parser parser;
        try
        {
            parser = new Parser(connection);
        }
        catch (ParserException e)
        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
        }

        if (value1.equals("roseindia") && value2.equals("roseindia"))
        {
            // NextPage page = new NextPage();
            // page.setVisible(true);
            // JLabel label = new JLabel("Welcome:" + value1);
            // page.getContentPane().add(label);
        }
        else
        {
//            System.out.println("enter the valid username and password");
//            JOptionPane.showMessageDialog(this, "Incorrect login or password",
//                    "Error", JOptionPane.ERROR_MESSAGE);
//            getImage();
        }
        
        String appURL="http:///..login.aspx";
        PostMethod post= new PostMethod(appURL);
        post.addRequestHeader("Cookie", "ASP.NET_SessionId=ahdoxxfc4df5jlbjvpqzpz55");
        post.setRequestBody("Username=crouprat&Password=Y5TfTT5W&VerificationCode=55kt&Referrer=www.sratim.co.il");
        HttpClient _httpClient=new HttpClient();
        try
        {
            _httpClient.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            for (int i = 0; i < headers.length; i++)
            {
                Header headerName = headers[i];
                if (headerName.getName().equalsIgnoreCase(SET_COOKIE))
                {
                    
                }
            }
        } catch (HttpException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        _manager.parseCookies(connection);
        String headerName=null;
        Map cookie = new HashMap();
        for (int i=1; (headerName = connection.getHeaderFieldKey(i)) != null; i++) {
            StringTokenizer st = null;
            if (headerName.equalsIgnoreCase(SET_COOKIE)) 
            {
                st = new StringTokenizer(connection.getHeaderField(i), COOKIE_VALUE_DELIMITER);
            }
            else if (headerName.equalsIgnoreCase("Cookie")) 
            {
                st = new StringTokenizer(connection.getHeaderField(i), COOKIE_VALUE_DELIMITER);
            }

            if (st != null)
            {                
                // the specification dictates that the first name/value pair
                // in the string is the cookie name and value, so let's handle
                // them as a special case: 

                if (st.hasMoreTokens()) {
                    String token  = st.nextToken();
                    String name = token.substring(0, token.indexOf(NAME_VALUE_SEPARATOR));
                    String value = token.substring(token.indexOf(NAME_VALUE_SEPARATOR) + 1, token.length());
                    //                domainStore.put(name, cookie);
                    cookie.put(name, value);
                }

                while (st.hasMoreTokens()) {
                    String token  = st.nextToken();
                    if (token.indexOf(NAME_VALUE_SEPARATOR) == -1)
                    {
                        continue;
                    }
                    cookie.put(token.substring(0, token.indexOf(NAME_VALUE_SEPARATOR)).toLowerCase(),
                            token.substring(token.indexOf(NAME_VALUE_SEPARATOR) + 1));
                }
            }
        }
    }
}

class LoginDemo
{
    public static void main(String arg[])
    {
        try
        {
            Login frame = new Login();
            frame.setSize(500, 400);
            frame.setVisible(true);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}