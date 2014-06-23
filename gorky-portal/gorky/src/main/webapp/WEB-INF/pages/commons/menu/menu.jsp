<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/commons/taglibs.jsp" %>
<div class="accordion" id="main-menu">
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse" data-parent="#main-menu" href="#">
                Informacje
            </a>
        </div>
    </div>
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse" data-parent="#main-menu" href="#obsluga">
                Moje konto
            </a>
        </div>
        <div id="obsluga" class="accordion-body collapse">
            <div class="accordion-inner">
                Podgląd konta
            </div>
            <div class="accordion-inner">
                Edycja konta
            </div>
            <div class="accordion-inner">
                Podgląd postaci
            </div>
        </div>
    </div>
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse" data-parent="#main-menu" href="#">
                Przeglądaj
            </a>
        </div>
    </div>
</div>
