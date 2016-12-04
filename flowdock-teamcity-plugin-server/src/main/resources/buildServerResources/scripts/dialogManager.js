  function showFlowdockDialog(message) {
     jQuery("#fl-dialog p").text(message);
     jQuery("#fl-dialog").dialog("open");
  }

  jQuery( function() {
     jQuery( "#fl-dialog" ).dialog({
      modal: true,
      autoOpen: false,
      buttons: {
        Ok: function() {
          jQuery( this ).dialog( "close" );
        }
      }
    });
  } );