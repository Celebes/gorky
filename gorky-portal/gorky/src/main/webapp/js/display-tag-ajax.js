(function ( $ )
{

    $.fn.displayTagAjax = function ()
    {
        var links = [];
        var container = this;
        addItemsToArray( this.find( "table .sortable a" ), links );
        addItemsToArray( this.find( ".pagelinks a" ), links );
        $.each( links, function ()
            {
                var url = $( this ).attr( "href" );
                addClickEvent( container, this, url );
                $( this ).removeAttr( "href" );
            }
        );
        return true;
    };

    function addClickEvent( ctx, element, url )
    {
        $( element ).click(
            function ()
            {
                jQuery.ajax(
                    {
                        url: url,
//                        async:false,
                        success: function ( data )
                        {
                            $( this ).html( data );
                            $( this ).displayTagAjax();
                        },
                        context: ctx
                    } );
            }
        );
    }

    function addItemsToArray( items, array )
    {
        $.each( items, function ()
            {
                array.push( this );
            }
        );
    }

})( jQuery );



