<%--
	Author: Kamil Szokaluk (kamil.szokaluk@execon.pl)
	Created: 25.09.13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/commons/taglibs.jsp"%>

<display:table class="table table-bordered" id="exampleId" size="listaSize" sort="external" partialList="true" pagesize="5"  name="lista"
    requestURI="">
    <display:column property="id" sortable="true"/>
    <display:column property="text"/>
</display:table>
