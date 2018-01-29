<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<TITLE>添加联系人</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/visit_add.action"
		method=post >
		

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						 height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：拜访管理 &gt; 添加客户拜访</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<tr>
								<td>所属用户：</td>
								<td>
								<select name="user.uid">
									<option>----所属用户----</option>
									<c:forEach items="${requestScope.listUser}" var="user">
										<option  value="${user.uid}" >${user.username}</option>
									</c:forEach>
								</select>
								</td>
								<td>拜访客户：</td>
								<td>
								<select name="customer.cid">
									<option>----拜访客户----</option>
									<c:forEach items="${requestScope.listCustomer}" var="customer">
										<option  value="${customer.cid}" >${customer.custName}</option>
									</c:forEach>
								</select>
								</td>
							</tr>
							<TR>
								<td>拜访日期：</td>
								<td>
								<INPUT class=textbox id=sChannel2 type="date"
														style="WIDTH: 120px" maxLength=50 name="visitDate">
								</td>
								<td>拜访地址：</td>
								<td>
								<INPUT class=textbox id=sChannel2 
														style="WIDTH: 180px" maxLength=50 name="visitAddress">
								</td>
							</TR>
							<TR>
								<td>拜访内容 ：</td>
								<td>
								<textarea  style="resize: none;" cols="50" rows="5"  name="visitContent" placeholder="最多255个字"></textarea>
								
							</TR>
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value="保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align="center" width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
