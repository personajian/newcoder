JSTL 核心标签库标签共有13个，功能上分为4类：

1. 表达式控制标签：**out、set、remove、catch**

2. 流程控制标签：**if、choose、when、otherwise**

3. 循环标签：**forEach、forTokens**

4. URL操作标签：**import、url、redirect**

使用标签时，一定要在jsp文件头加入以下代码：
```
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```
使用JSTL之前，需要下载下面两个jar文件，然后放到web应用的WEB-INF/lib目录。
```
jstl.jar
standard.jar
```
下面分别对这些标签进行说明：
## 表达式控制标签
表达式控制分类中包括<c: out>、
<c: set>、<c:remove>、<c:chtch>4个标签，现在分别介绍它们的功能和语法。
### 1．<c: out>标签

【功能】：用来显示数据对象（字符串、表达式）的内容或结果。

在使用Java脚本输出时常使用的方式为：
```
<% out.println("字符串")%>

<%=表达式%>
```

在web开发中，为了避免暴露逻辑代码会尽量减少页面中的Java脚本，使用<c: out>标签就可以实现以上功能。
```
<c:out value="字符串">

<c:out value="EL表达式">
```

**提示**：JSTL的使用是和EL表达式分不开的，EL表达式虽然可以直接将结果返回给页面，但有时得到的结果为空，<c: out>有特定的结果处理功能，EL的单独使用会降低程序的易读性，建议把EL的结果输入放入<c: out>标签中。

##### <c: out>标签的使用有两种语法格式

【语法1】：
```
<c:out value="要显示的数据对象" [escapeXml="true|false"] [default="默认值"]>

```
【语法2】：
```
<c:out value="要显示的数据对象" [escapeXml="true|false"]>
默认值
</c:out>
```
这两种方式没有本质的区别，只是格式上的差别。标签的属性介绍如下。

- value：指定要输出的变量或表达式。

- escapeXml：设定是否转换特殊字符（如&lt、&gt等一些转义字符），在默认值为true的情况下直接在输出&lt的，如果改为false将会进行转义输出“<”等。

- default：为默认输出结果。如果使用表达式得到的结果为null（注意与空区别），将会输出默认结果。

例如：  
```
<%@ page pageEncoding="gbk" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>out标签的使用</title>
</head>
<body>
	<li>（1）<c:out value="北京源智天下科技有限公司"></c:out></li>
	<li>（2）<c:out value="&lt未使用字符转义&gt" /></li>
	<li>（3）<c:out value="&lt使用字符转义&gt" escapeXml="false"></c:out></li>
	<li>（4）<c:out value="${null}">使用了默认值</c:out></li>
	<li>（5）<c:out value="${null}"></c:out></li>
</body>
</html>
```
显示效果:
```
•（1）北京源智天下科技有限公司
•（2）&lt未使用字符转义&gt
•（3）<使用字符转义>
•（4）使用了默认值
•（5）

```

### 2．<c: set>标签

【功能】：主要用于将变量存取于JSP范围中或JavaBean属性中。

##### <c: set>标签的编写共有4种语法格式。

【语法1】：存值，把一个值放在指定（page、session等）的map中。
```
<c:set value="值1" var="name1" [scope="page|request|session|application"]>

```
含义：把一个变量名为name1值为“值1”的变量存储在指定的scope范围内。

【语法2】：
```
<c:set var="name2" [scope="page|request|session|application"]>
值2
</c:set>
```

含义：把一个变量名为name2，值为"值2"的变量存储在指定的scope范围内。

【语法3】：
```
<c:set value="值3" target="JavaBean对象" property="属性名"/>
```
含义：把一个值为"值3"赋值给指定的JavaBean的属性名，相当于1setter()方法。

【语法4】：
```
<c:set target="JavaBean对象" property="属性名">
值4
</c:set>
```
含义：把一个"值4"赋值给指定的JavaBean的属性名。

提示：功能上，语法1和语法2、语法3和语法4的效果是一样的，只是把value值放置的位置不同。至于使用哪个根据个人的喜爱，语法1和语法2是向scope范围内存储一个值，语法3和语法4是给指定的JavaBean赋值。  

示例：
```
使用<c:set>存取值

<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>set标签的使用</title>
</head>
<body>
	<li>把一个值放入session中。</li>
	<c:set value="coo" var="name1" scope="session"></c:set>
	<li>从session中得到值:${sessionScope.name1}</li>
	<li>把另一个值放入application中。</li>
	<c:set var="name2" scope="application">olive</c:set>
	<li>使用out标签和EL表达式嵌套得到值：<c:out value="${applicationScope.name2}">未得到name的值</c:out>
	</li>
	<li>未指定scope的范围，会从不同的范围内查找得到相应的值：${name1},${name2}</li>
</body>
</html>
```
显示效果：
```
•把一个值放入session中。
•从session中得到值:coo
•把另一个值放入application中。
•使用out标签和EL表达式嵌套得到值：olive 
•未指定scope的范围，会从不同的范围内查找得到相应的值：coo,olive

```
示例：Java脚本实现值的存取
```

<%@page language="java" pageEncoding="gbk"%>
<html>
<head>
<title>set标签的使用</title>
</head>
<body>
<li>把一个值放入session中。<%session.setAttribute("name1","coo"); %></li>
<li>从session中得到值:<% out.println(session.getAttribute("name1"));   %></li>
<li>把另一个值放入application中。<% application.setAttribute("name2","olive"); %></li>
<li> 从application中得到值：<% out.println(application.getAttribute("name2")); %></li>
</body>
</html>

```

###### 提示：
本章示例为了方便期间都是从一个页面中存取，在开发中值的存取是为了不同的JSP页面之间共享数据
从两个程序对比来看，JSTL实习了使用标签完成取值赋值的功能，减少代码的编写量同时避免了逻辑代码暴露的危险。

示例：演示使用<c: set标签>操纵JavaBean
```
package org.olive;

public class Person {
	private String name;         //定义私有变量姓名字符串
	private int age;             //定义私用变量年龄整型
	private char sex;            //定义私用变量性别字符性
	private String home;         //定义私用变量家乡字符串
	public String getName() {    
		return name;
	}

	public void setName(String name) {    
		this.name = name;
	}
	public int getAge() {                                      
		return age;
	}
	public void setAge(int age) {                       
		this.age = age;
	}
	public char getSex() {                                  
		return sex;
	}
	public void setSex(char sex) {                     
		this.sex = sex;
	}
	public String getHome() {                          
		return home;
	}
	public void setHome(String home) {                   
		this.home = home;
	}
}

```
```
<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="person" class="org.olive.Person" />
<html>
<head>
<title>set标签的使用</title>
</head>
<body>
	<c:set target="${person}" property="name">maverick</c:set>
	<c:set target="${person}" property="age">25</c:set>
	<c:set target="${person}" property="sex">男</c:set>
	<c:set target="${person}" property="home">china</c:set>
	<li>使用的目标对象为：${person }
	<li>从Bean中获得的name值为：<c:out value="${person.name}"></c:out>
	<li>从Bean中获得的age值为：<c:out value="${person.age}"></c:out>
	<li>从Bean中获得的sex值为：<c:out value="${person.sex}"></c:out>
	<li>从Bean中获得的home值为：<c:out value="${person.home}"></c:out>
</body>
</html>
```
运行结果：
```
•使用的目标对象为：org.olive.Person@4168a86 
•从Bean中获得的name值为：maverick 
•从Bean中获得的age值为：25 
•从Bean中获得的sex值为：男 
•从Bean中获得的home值为：china 

```
###### 注意：
使用target时一定要指向实例化后的JavaBean对象，也就是要跟<jsp:useBean>配套使用，也可以java脚本实例化，但这就失去了是用标签的本质意义。

使用Java脚本实例化：
```
<%@page import="org.olive.Person"%>
<% Person person=new Person(); %>
```
### 3．<c:remove>标签
【功能】：主要用来从指定的JSP范围内移除指定的变量。  

【语法】：
```
<c:remove var="变量名" [scope="page|request|session|application"]/>
```

其中var属性是必须的，scope可以以省略。

示例：
```
<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>remove标签的使用</title>
</head>
<body> 
	<c:set var="name" scope="session">olive</c:set>
	<c:set var="age" scope="session">25</c:set>
	<c:set var="sex" scope="session">男</c:set>
	<li><c:out value="${sessionScope.name}"></c:out>
	<li><c:out value="${sessionScope.age}"></c:out>
	<li><c:out value="${sessionScope.sex}"></c:out>
	<c:remove var="age"/>
	<li><c:out value="${sessionScope.name}"></c:out>
	<li><c:out value="${sessionScope.age}"></c:out>
	<li><c:out value="${sessionScope.sex}"></c:out>
</body>
</html>

```

运行效果:
```
•olive 
•25 
•男 
•olive 
• 
•男 

```

### 4．<c:catch>标签

【功能】：用来处理JSP页面中产生的异常，并将异常信息存储。

【语法】：
```
<c:catch var="name1">

容易产生异常的代码

</c:catch>
```
【参数说明】：

var表示由用户定义存取异常信息的变量的名称。省略后也可以实现异常的捕获，当就不能显示的输出异常信息。

示例：
```
<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>JSTL: -- catch标签实例</title>
</head>
<body>
	<h4>catch标签实例</h4>
	<hr>
	<c:catch  var="error">
	<c:set target="Dank" property="hao"></c:set>
	<</c:catch>
	<c:out value="${error}"/>
</body>
</html>
```
运行效果:
```
catch标签实例
-------------------------------------------------------------------------------------------
javax.servlet.jsp.JspTagException: Invalid property in &lt;set&gt;: "hao" 
```

---

## 流程控制标签

流程控制标签主要用于对页面简单业务逻辑进行控制。流程控制标签包含有4个：<c:if>标签、<c:choose>标签、<c:when>标签和<c: otherwise>标签。下面将介绍这些标签的功能和使用方式。

### 1．<c:if>标签

【功能】： 同程序中的if作用相同，用来实现条件控制。

【语法1】：
```
<c:if test="条件1" var="name" [scope="page|request|session|application"]>
```
【语法2】：
```
<c:if test="条件2" var="name"[scope="page|request|session|application"]>
```
【参数说明】：

（1）test属性用于存放判断的条件，一般使用EL表达式来编写。

（2）var指定名称用来存放判断的结果类型为true或false。

（3）scope用来存放var属性存放的范围。

【使用场景】：在开发中经常会出现不同用户的权限，首先对用户名进行判断（包括进行数据库验证，该功能可以由JavaBean实现，使用EL表达式得到一个布尔型的结果），把判断的结果存放在不同的JSP范围内（比如常用的session内），这样在每个页面都可以得到该用户的权限信息，根据不同权限的用户显示不同的结果。

示例：
```
<!-- main.jsp-->
<!-- 实现了用户输入用户名提交到自身页面，页面判断用户是否为admin，如果是将出现欢迎界面，如果不是显示不同结果。-->
<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>JSTL: -- if标签示例</title>
</head>
<body>
	<h4>if标签示例</h4>
	<hr>
	<form action="main.jsp" method="post">
	    <input type="text" name="uname" value="${param.uname}">
	    <input type="submit" value="登录">
	</form>
	<c:if test="${param.uname=='admin'}" var="adminchock">
	    <c:out value="管理员欢迎您！">
	    </c:out>
	</c:if>
	${adminchock}
</body>
</html>

```

### 2．<c:choose>、<c:when>和<c: otherwise>标签

这3个标签通常情况下是一起使用的，<c:choose>标签作为<c:when>和<c: otherwise>标签的父标签来使用。

【语法1】：
```
<c:choose>

    <c:when>

…..//业务逻辑1

    <c:otherwise>

…..//业务逻辑2

    <c:otherwise>

….//业务逻辑3

</c:choose>
```
【语法2】：
```
<c:when text="条件">

表达式

</c:when>
```
【语法3】：
```
<c:otherwise>

表达式

</c:otherwise>
```
【参数说明】：

（1）语法1为3个标签的嵌套使用方式，<c:choose>标签只能和<c:when>标签共同使用。

（2）语法2为<c:when>标签的使用方式，该标签都条件进行判断，一般情况下和<c:choose>共同使用。

（3）<c: otherwise>不含有参数，只能跟<c:when>共同使用，并且在嵌套中只允许出现一次。

示例:
```
<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>JSTL: -- choose及其嵌套标签标签示例</title>
</head>
<body>
	<h4>choose及其嵌套标签示例</h4>
	<hr>
	<c:set var="score">85</c:set>
	<c:choose>
		<c:when test="${score>=90}">
		你的成绩为优秀！
		</c:when>
		<c:when test="${score>=70&&score<90}">
		您的成绩为良好!
		</c:when>
		<c:when test="${score>60&&score<70}">
		您的成绩为及格
		</c:when>
		<c:otherwise>
		对不起，您没有通过考试！
		</c:otherwise>
	</c:choose>
</body>
</html>
```
运行效果：
```
choose及其嵌套标签示例

您的成绩为良好! 
```

## 3 循环标签

循环标签主要实现迭代操作。主要包含两个标签：<c:forEach>和<c:forTokens>标签，接下来将详细介绍这两个标签的用法。

### 1．<c:forEach>标签

该标签根据循环条件遍历集合（Collection）中的元素。

【语法】：
```
<c:forEach var="name" items="Collection" varStatus="StatusName" begin="begin" end="end" step="step">
本体内容
</c:forEach>
```
【参数解析】：

（1）var设定变量名用于存储从集合中取出元素。

（2）items指定要遍历的集合。

（3）varStatus设定变量名，该变量用于存放集合中元素的信息。    

（4）begin、end用于指定遍历的起始位置和终止位置（可选）。

（5）step指定循环的步长。

**表1 循环标签属性说明**

名称 |  EL | 类型 | 是否必须 | 默认值
---|--- | --- | --- | ---
var | N | String | 是 | 无
items | Y | Arrays Collection Iterator Enumeration Map String[] args | 是 | 无
begin | Y | int | 否 | 0
end   | Y | int | 否 | 集合中最后一个元素
step  | Y | int | 否 | 1
varStatus | N | String | 否 | 无

其中varStatus有4个状态属性  

**表2 varStatus的4个状态**  

属性名 | 类型 | 说明
--- | --- | ---
index | int | 当前循环的索引值
count | int | 循环的次数
first | boolean | 是否为第一个位置
last  | boolean | 是否为第二个位置

示例：
```
1<%@ page contentType="text/html;charset=GBK" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>JSTL: -- forEach标签实例</title>
</head>
<body>
	<h4><c:out value="forEach实例"/></h4>
	<hr>
	<%
		List a = new ArrayList();
		a.add("贝贝");
		a.add("晶晶");
		a.add("欢欢");
		a.add("莹莹");
		a.add("妮妮");
		request.setAttribute("a", a);
    %>
	<B><c:out value="不指定begin和end的迭代：" /></B><br>
	<c:forEach var="fuwa" items="${a}">
	&nbsp;<c:out value="${fuwa}"/><br>
	</c:forEach>
	
	<B><c:out value="指定begin和end的迭代：" /></B><br>
	<c:forEach var="fuwa" items="${a}" begin="1" end="3" step="2">
	&nbsp;<c:out value="${fuwa}" /><br>
 	</c:forEach>

    <B><c:out value="输出整个迭代的信息：" /></B><br>
	<c:forEach var="fuwa" items="${a}" begin="3" end="4" step="1" varStatus="s">
	&nbsp;<c:out value="${fuwa}" />的四种属性：<br>
	&nbsp;&nbsp;所在位置，即索引：<c:out value="${s.index}" /><br>
	&nbsp;&nbsp;总共已迭代的次数：<c:out value="${s.count}" /><br>
	&nbsp;&nbsp;是否为第一个位置：<c:out value="${s.first}" /><br>
	&nbsp;&nbsp;是否为最后一个位置：<c:out value="${s.last}" /><br>
	</c:forEach>

</body>
</html>
```
```
forEach实例

不指定begin和end的迭代：
 贝贝
 晶晶
 欢欢
 莹莹
 妮妮
指定begin和end的迭代：
 晶晶
 莹莹
输出整个迭代的信息：
 莹莹的四种属性：
  所在位置，即索引：3
   总共已迭代的次数：1
   是否为第一个位置：true
   是否为最后一个位置：false
  妮妮的四种属性：
  所在位置，即索引：4
   总共已迭代的次数：2
   是否为第一个位置：false
   是否为最后一个位置：true
```

【总结】：

（1）从图中可以看到不使用begin和end的迭代，从集合的第一个元素开始，遍历到最后一个元素。

（2）指定begin的值为1、end的值为3、step的值为2，从第二个开始首先得到晶晶，每两个遍历一次，则下一个显示的结果为莹莹，end为3则遍历结束。

（3）从指定的begin和end的值来看遍历第四个和第五个，因得到莹莹和妮妮。相关状态信息如图所示。

提示：本例使用的list是在JSP页面中使用Java脚本创建的，是因为JSTL缺少创建集合的功能，在开发中一般不会如此，可通过访问数据库得到数据集合和通过设定JavaBean的值得到数据集合。

### 2．<c:forTokens>
该标签用于浏览字符串，并根据指定的字符将字符串截取。

语法：
```
<c:forTokens items="strigOfTokens" delims="delimiters" [var="name" begin="begin" end="end" step="len" varStatus="statusName"] >
```
【参数说明】

（1）items指定被迭代的字符串。

（2）delims指定使用的分隔符。

（3）var指定用来存放遍历到的成员。

（4）begin指定遍历的开始位置（int型从取值0开始）。

（5）end指定遍历结束的位置（int型，默认集合中最后一个元素）。

（6）step遍历的步长（大于0的整型）。

（7）varStatus存放遍历到的成员的状态信息。

示例：
```
<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>JSTL: -- forTokens标签实例</title>
</head>
<body>
	<h4><c:out value="forToken实例"/></h4>
	<hr>
	<c:forTokens items="北、京、欢、迎、您" delims="" var="c1">
		<c:out value="${c1}"></c:out>
	</c:forTokens><br>
	<c:forTokens items="123-4567-8854" delims="-" var="t">
		<c:out value="${t}"></c:out>
	</c:forTokens><br>
	<c:forTokens items="1*2*3*4*5*6*7" delims="*" begin="1" end="3" var="n" varStatus="s">
		&nbsp;<c:out value="${n}" />的四种属性：<br>
		&nbsp;&nbsp;所在位置，即索引：<c:out value="${s.index}" /><br>
		&nbsp;&nbsp;总共已迭代的次数：<c:out value="${s.count}" /><br>
		&nbsp;&nbsp;是否为第一个位置：<c:out value="${s.first}" /><br>
		&nbsp;&nbsp;是否为最后一个位置：<c:out value="${s.last}" /><br>
	</c:forTokens>
</body>
</html>
```
运行效果：
```
forToken实例

北、京、欢、迎、您 
123 4567 8854 
  2的四种属性：
  所在位置，即索引：1
   总共已迭代的次数：1
   是否为第一个位置：true
   是否为最后一个位置：false
  3的四种属性：
  所在位置，即索引：2
   总共已迭代的次数：2
   是否为第一个位置：false
   是否为最后一个位置：false
  4的四种属性：
  所在位置，即索引：3
   总共已迭代的次数：3
   是否为第一个位置：false
   是否为最后一个位置：true
```

## 4 URL操作标签

JSTL包含3个与URL操作有关的标签，分别为：<c:import>、<c:redirect>和<c:url>标签。它们的作用为：显示其他文件的内容、网页导向、产生URL。下面将详细介绍这3个标签的使用方法。  

### 1．<c:import>标签

该标签可以把其他静态或动态文件包含到本JSP页面。同<jsp:include>的区别为：只能包含同一个web应用中的文件。而<c:import>可以包含其他web应用中的文件，甚至是网络上的资源。

语法1：
```
<c:import url="url" [context="context"][ value="value"]

[scope="page|request|session|application"] [charEncoding="encoding"]>
```
语法2：
```
<c:import url="url" varReader="name" [context="context"][charEncoding="encoding"]>
```
###### 表3 <c:import>标签参数说明
名称 | 说明 | EL | 类型 | 必须 | 默认值  
---| --- | --- | --- | --- | --- 
url |被导入资源的URL路径 |Y|String|是|无
context|相同服务器下其他的web工程，必须以“ " ”开头|Y|String|否|无
var|以String类型存入被包含文件的内容|N|String|否|无
Scope|var变量的JSP范围|N|String|否|page
charEncoding|被导入文件的编码格式|Y|String|否|无
varReader|以Reader类型存储被包含文件内容|N|String|否|无

【参数说明】：

（1）URL为资源的路径，当应用的资源不存在时系统会抛出异常，因此该语句应该放在<c:catch></c:catch>语句块中捕获。应用资源有两种方式：绝对路径和相对路径。使用绝对路径示例如下：
```<c:import url="http://www.baidu.com">```

使用相对路径的实例如下：
```
<c:import url="aa.txt">
```
aa.txt放在同一文件目录。

如果以“/”开头表示应用的根目录下。例如：tomcat应用程序的根目录文件夹为webapps。导入webapps下的文件bb.txt的编写方式为：
```
<c:import url="/bb.txt">
```
如果访问webapps管理文件夹中其他web应用就要用context属性。

（2）context属性用于在访问其他web应用的文件时，指定根目录。例如，访问root下的index.jsp的实现代码为：
```
<c:import url="/index.jsp" context="/root">
```
等同于webapps/root/index.jsp

（3）var、scope、charEncoding、varReader是可选属性。具体使用方式见示例代码。

示例：
```
 <%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>JSTL: -- import标签实例</title>
</head>
<body>
	<h4><c:out value="import实例"/></h4>
	<hr>
	<h4><c:out value="绝对路径引用的实例" /></h4>
	<c:catch var="error1">
		<c:import url="http://www.baidu.com"/>
	</c:catch>
	<c:out value="${error1}"></c:out>
	<hr>
	<h4>
	<c:out value="相对路径引用的实例，引用本应用中的文件" /></h4>
	<c:catch>
		<c:import url="a1.txt" charEncoding="gbk"/>
	</c:catch>
	<hr>
	<h4><c:out value="使用字符串输出、相对路径引用的实例，并保存在session范围内" /></h4>
	<c:catch var="error3">
		<c:import var="myurl" url="a1.txt" scope="session" charEncoding="gbk"></c:import>
		<c:out value="${myurl}"></c:out>
		<c:out value="${myurl}" />
	</c:catch>
	<c:out value="${error3}"></c:out>
</body>
</html>
```

### 2．<c:redirect>标签

该标签用来实现了请求的重定向。同时可以在url中加入指定的参数。例如：对用户输入的用户名和密码进行验证，如果验证不成功重定向到登录页面；或者实现web应用不同模块之间的衔接。  

【语法1】：
```
<c:redirect url="url" [context="context"]>
```
【语法2】：
```
<c:redirect url="url"[context="context"]>

<c:param name="name1" value="value1">

</c:redirect>
```
【参数说明】：

（1）url指定重定向页面的地址，可以是一个string类型的绝对地址或相对地址。

（2）用于导入其他web应用中的页面。


示例：

```
<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:redirect url="http://127.0.0.1:8080">
<c:param name="uname">olive</c:param>
<c:param name="password">01234</c:param>
</c:redirect>
```
【代码解析】

（1）使用重定向与载入页面不同，载入页面时在本页面中插入其他页面，而重定向是请求转发，等于在页面中重新输入了一次url。当重定向到某个页面时浏览器中的地址会发生变化。

（2）使用重定向时不用使用<c:catch>语句，当输入页面访问不到时，浏览器会报错，跟程序运行无关。如果使用重定向时页面定义的内容将不会得到显示。

（3）在重定向时为URL添加了两个参数和参数值：uname=olive和password=01234。


### 3．<c:url>标签

该标签用于动态生成一个String类型的URL，可以同<c:redirect>标签共同使用，也可以使用html的<a>标签实现超链接。

【语法1】：指定一个url不做修改，可以选择把该url存储在JSP不同的范围中。
```
<c:url value="value" [var="name"][scope="page|request|session|application"]

[context="context"]/>
```
【语法2】：给url加上指定参数及参数值，可以选择以name存储该url。
```
<c:url value="value" [var="name”][scope="page|request|session|application"]

[context="context"]>

<c:param name="参数名" value="值">

</c:url>

```
示例：
```
<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
</head>
<body>
	<c:out value="url标签使用"></c:out>
	<h4>使用url标签生成一个动态的url，并把值存入session中.</h4>
	<hr>
	<c:url value="http://127.0.0.1:8080" var="url" scope="session">
	</c:url>
	<a href="${url}">Tomcat首页</a>
</body>
</html>
```