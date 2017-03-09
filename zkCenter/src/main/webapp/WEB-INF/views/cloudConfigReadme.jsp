<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>css/prettify.min.css" type="text/css" rel="stylesheet">
<style type="text/css">
  h2 a {
    color: #337ab7;
  }
  h2 a:hover {
    color: #337ab7;
  }
</style>
<script type="text/javascript" src="<%=basePath%>js/prettify.js"></script>
<title>云配置接入须知</title>
</head>
<body>
<body class="nav-md" onload="prettyPrint()">
  <div class="container body">
    <div class="main_container">
      <jsp:include page="./menu.jsp"></jsp:include>
      <div class="right_col" role="main">
        <div class="row">
          <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
              <div class="x_title">
                <h2>云配置接入须知（目前只支持Java客户端接入）</h2>
                <div class="clearfix"></div>
              </div>
              <div class="x_content">
                <ul class="list-unstyled timeline">
                  <li>
                    <div class="block">
                      <div class="tags">
                        <a href="#" class="tag">
                          <span>Step1:</span>
                        </a>
                      </div>
                      <div class="block_content">
                        <h2 class="title"><a>下载接入工具包</a></h2>
                        <div class="byline"></div>
                        <p class="excerpt">
                          <a style="color: rgb(59, 182, 243);" href="<%=basePath%>files/config-client.zip">点击此处</a>下载jar包。
                          此jar包中包含了客户端所依赖的所有jar包，下载后直接引入项目即可使用。
                        </p>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="block">
                      <div class="tags">
                        <a href="#" class="tag">
                          <span>Step2.1:</span>
                        </a>
                      </div>
                      <div class="block_content">
                        <h2 class="title"><a>创建链接池--Spring方式配置（推荐）</a></h2>
                        <div class="byline"></div>
                        <p class="excerpt">
                                                    在Spring配置文件中加入如下配置项
                          <pre>
&lt;bean id="zkConnector" class="com.xxx.xxx.ZKConnnector"&gt;
  &lt;constructor-arg name="ip"&gt;192.168.1.100&lt;/constructor-arg&gt;
  &lt;constructor-arg name="port"&gt;8081&lt;/constructor-arg&gt;
  &lt;constructor-arg name="sessionTimeoutMs"&gt;60000&lt;/constructor-arg&gt;
  &lt;constructor-arg name="connectionTimeoutMs"&gt;60000&lt;/constructor-arg&gt;
&lt;/bean&gt;</pre>
或
<pre>
&lt;bean id="zkConnector" class="com.xxx.xxx.ZKConnnector"&gt;
  &lt;constructor-arg name="ip"&gt;192.168.1.100&lt;/constructor-arg&gt;
  &lt;constructor-arg name="port"&gt;8081&lt;/constructor-arg&gt;
&lt;/bean&gt;
</pre>
ip：为接入zookeeper服务器的ip地址；<br>
port：为接入zookeeper服务器的端口；<br>
sessionTimeoutMs：与zookeeper链接的session超时时间，单位毫秒；<br>
connectionTimeoutMs：与zookeeper链接的超时时间，单位毫秒；<br><br>
然后在你需要监听配置项的地方通过spring注入此bean，例如下面方式：
<pre class="prettyprint linenums">
public class listenConfig
{
    @Resource
    ZKConnnector zkConnector;
}
</pre>
                        </p>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="block">
                      <div class="tags">
                        <a href="#" class="tag">
                          <span>Step2.2:</span>
                        </a>
                      </div>
                      <div class="block_content">
                        <h2 class="title"><a>创建链接池--代码方式接入</a></h2>
                        <div class="byline">
                        </div>
                        <p class="excerpt">
                        代码方式接入需要你编写相应代码，获取配置连接池，再从连接池中获取链接做具体操作，例如以下方式：
<pre class="prettyprint linenums">
public class listenConfig
{
    public ZKConnector getConnectionPool()
    {
        return new ZKConnector("192.168.140.129", 2181, 1*60*1000, 2*60*1000);
    }
}
</pre>
或
<pre class="prettyprint linenums">
public class listenConfig
{
    public ZKConnector getConnectionPool()
    {
        return new ZKConnector("192.168.140.129", 2181);
    }
}
</pre>
                        </p>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="block">
                      <div class="tags">
                        <a href="#" class="tag">
                          <span>Step3:</span>
                        </a>
                      </div>
                      <div class="block_content">
                        <h2 class="title"><a>获取连接，监听配置</a></h2>
                        <div class="byline">
                        </div>
                        <p class="excerpt">
                        最后一步即为从连接池中获取链接，并监听相关配置项；监听时，可监听某一项配置，同时也可监听系统下所有配置项数据。<br>
                        监听时定义一个全局的map，用于存放系统所有配置项，如果配置项值改变，系统会立即自动更新配置值，应用再次获取配置时即可获取到最新配置项数据。
<pre class="prettyprint linenums">
public class App
{
    @Resource
    ZKConnector zkConnector;
    
    public static Map<String, String> configData = new ConcurrentHashMap<String, String>();
    
    /**
     * @description: 监听一个配置项
     * @author: Wind-spg
     * @param args
     */
    public static void main(String[] args)
    {
        CuratorFramework client = zkConnector.getConnector();
        try
        {
            ZKUtil.watchOneConfigData(client, "systemName", "configKey", configData);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
</pre>
或
<pre class="prettyprint linenums">
public class App
{
    @Resource
    ZKConnector zkConnector;
    
    public static Map<String, String> configData = new ConcurrentHashMap<String, String>();
    
    /**
     * @description: 监听一个配置项
     * @author: Wind-spg
     * @param args
     */
    public static void main(String[] args)
    {
        CuratorFramework client = zkConnector.getConnector();
        try
        {
            ZKUtil.watchAllConfigData(client, "systemName", configData);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
</pre>
                        </p>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <br />
      </div>
    </div>
  </div>
</body>
</html>