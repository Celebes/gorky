jQuery.noConflict();

jQuery( document ).ready( function ( $ )
{
    $( "#session-timeout" ).countdown( {format: "MS", description: '', compact: true, until: sessionTimeout, onExpiry: function ()
    {
        $( this ).text( "Twoja sesja wygasła" ).css( "color", "#EC0000" );
    }} );
//    $.fn.serializeObject = function ()
//    {
//        var o = {};
//        var a = this.serializeArray();
//        jQuery.each( a, function ()
//        {
//            if ( o[this.name] )
//            {
//                if ( !o[this.name].push )
//                {
//                    o[this.name] = [o[this.name]];
//                }
//                o[this.name].push( this.value || '' );
//            } else
//            {
//                o[this.name] = this.value || '';
//            }
//        } );
//        return o;
//    };
//
//    $.postJSONWithHtmlResponse = function ( url, data, callback )
//    {
//        return jQuery.ajax( {
//            'type': 'POST',
//            'url': url,
//            'contentType': 'application/json;charset=utf-8',
//            'data': JSON.stringify( data ),
//            'success': callback
//        } );
//    };
//
//    $.fn.createTable = function ( url )
//    {
//        var container = jQuery( this );
//        $.get( url, function ( resp )
//        {
//            container.html( resp );
//            container.displayTagAjax();
//        } );
//    };
//
//    $.fn.searchPerson = function ( url )
//    {
//        $( this ).click( function ()
//        {
//            var target = $( this ).data( "target" ),
//                container = $( "#select" + target );
//
//            $.post( url, $( "#form" + target ).serialize(), function ( resp )
//            {
//                container.empty();
//                $( "#feedback" + target ).empty();
//                $( "#feedback" ).empty();
//                if ( resp.error )
//                {
//                    $.each( resp.msg, function ( i, v )
//                    {
//                        $( "#feedback" + target ).append( v + "<br/>" );
//                    } );
//                } else
//                {
//                    $.each( resp.resp, function ( i, v )
//                    {
//                        container.append( $( "<option/>" ).val( v.id ).text( v.fullName ) );
//                    } );
//                }
//            } );
//        } );
//    };
//
//    $( ".datepicker" ).datepicker( {
//        format: "yyyy-mm-dd"
//    } );
} );
