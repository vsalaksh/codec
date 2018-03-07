$(function(){
 $('#button').click(function(){
      //alert($('#input').val());
	  //alert($('#input').val());
	  //alert($('#endurl').val());
	  var response = '';
	  var input = $('#input').val();
	  $.ajax({ type: "POST",   
	  url: $('#endurl').val(),
	  crossDomain: true,
	  contentType: "application/json",
	   headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
	     dataType : "json",   
         data: JSON.stringify({"input":$('#input').val()}),
		 success : function(text)
         {
             //alert(text.output);
			 response = text.output;
			 $('#output').val(response);
         },
		 error : function(text, status, error)
		 {
		 alert(error);
		 }
});
//alert(response);
				
});
});