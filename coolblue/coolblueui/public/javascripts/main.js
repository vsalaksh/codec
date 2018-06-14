$(function(){

  $('#button').click(function(){
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
});

 $('#format').click(function(){
 var response = '';
	  var input = $('#input').val();
	  var endURL = $('#endurl').val();
	  //alert(input + endURL);
	  $.ajax({ type: "POST", url: $('#endurl').val(), crossDomain: true, contentType: "application/json", 
	   headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
	     dataType : "json",   
         data: JSON.stringify({"input":$('#input').val()}),
		 success : function(text)
         {
             $("pre").removeClass("prettyprinted");
			 response = text.output;
			 //$('#output').val(response);
			 response = response.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
			 //response = response.replace(/&lt;script src[\\s\S]*?&gt;&lt;\/script&gt;|&lt;!--\?[\s\S]*?--&gt;|&lt;pre\b[\s\S]*?&lt;\/pre&gt;/g, '<span class=operative>$&</span>');
			 
			 //document.getElementById("formatted").innerHTML = response;
			 $('#formatted').html(response);
			 PR.prettyPrint();
			 
         },
		 error : function(text, status, error)
		 {
		 alert(error);
		 }
		 
 
 });
 });
 
 $('#decompile').click(function(){
 var response = '';
	  //alert('Clicked Decompile');
	  var formval = new FormData();
jQuery.each(jQuery('#classfile')[0].files, function(i, file) {
    formval.append('file', file);
});
	  $.ajax({ type: "POST", url: $('#endurl').val(), crossDomain: true, contentType: false, processData: false, 
	    data: formval,
		 success : function(text)
         {
             $("pre").removeClass("prettyprinted");
			 response = text.decompiledCode;
			 $('#output').val(response);
			 response = response.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
			 //response = response.replace(/&lt;script src[\\s\S]*?&gt;&lt;\/script&gt;|&lt;!--\?[\s\S]*?--&gt;|&lt;pre\b[\s\S]*?&lt;\/pre&gt;/g, '<span class=operative>$&</span>');
			 
			 //document.getElementById("formatted").innerHTML = response;
			 $('#formatted').html(response);
			 PR.prettyPrint();
			 
         },
		 error : function(text, status, error)
		 {
		 alert(error);
		 }
 
 });
 });
 
 $("#classfile").change(function () {

  var fieldVal = $(this).val();

  // Change the node's value by removing the fake path (Chrome)
  fieldVal = fieldVal.replace("C:\\fakepath\\", "");

  $("#subfile").val(fieldVal);

});
 $('#copyBtn').click(function(){
 
 });		
});
