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
			 $('#textoutput').val(response);
			 //$('#output').val(response);
			 response = response.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
			 //response = response.replace(/&lt;script src[\\s\S]*?&gt;&lt;\/script&gt;|&lt;!--\?[\s\S]*?--&gt;|&lt;pre\b[\s\S]*?&lt;\/pre&gt;/g, '<span class=operative>$&</span>');
			 
			 //document.getElementById("formatted").innerHTML = response;
			 $('#output').html(response);
			 $('#textoutput').html(response);
			 PR.prettyPrint();
			 
         },
		 error : function(text, status, error)
		 {
		 alert(error);
		 }
		 
 
 });
 });
 
 $('#decompile').click(function(){
 $('#formatted').html('<div class="container"><div class="row"><div class="col-md-3 bg"> Decompiling in progresss<div class="loader" id="loader-6"><span></span><span></span><span></span></div></div></div>');
 var response = '';
	  //alert('Clicked Decompile');
	  var formval = new FormData();
jQuery.each(jQuery('#classfile')[0].files, function(i, file) {
    formval.append('file', file);
	$('#filename').val(file.name.split('.')[0] + '.java');
	//alert(file.name.split('.')[0]);
});
	  $.ajax({ type: "POST", url: $('#endurl').val(), crossDomain: true, contentType: false, processData: false, 
	    data: formval,
		 success : function(text)
         {
             $("pre").removeClass("prettyprinted");
			 response = text.decompiledCode;
			 $('#output').val(response);
			 $('#textoutput').val(response);
			 response = response.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
			 //response = response.replace(/&lt;script src[\\s\S]*?&gt;&lt;\/script&gt;|&lt;!--\?[\s\S]*?--&gt;|&lt;pre\b[\s\S]*?&lt;\/pre&gt;/g, '<span class=operative>$&</span>');
			 
			 //document.getElementById("formatted").innerHTML = response;
			 $('#formatted').html(response);
			 PR.prettyPrint();
			 
         },
		 error : function(text, status, error)
		 {
		 alert(error + text.text);
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
  var copyText = document.getElementById("output");
 alert($('#textoutput').val());
copyText.select();

 document.execCommand("copy");

 });
 $('#copyFormattedBtn').click(function(){
  var copyText = document.getElementById("textoutput").value;
 var tempInput = document.createElement("textarea");
    tempInput.style = "position: absolute; left: -1000px; top: -1000px";
    tempInput.value = copyText;
    document.body.appendChild(tempInput);
    tempInput.select();
    
    
 alert(copyText);
 document.execCommand("copy");
 document.body.removeChild(tempInput);

 });
 $('#downloadBtn').click(function(){
 var textToWrite = document.getElementById("textoutput").value;
 
    var textFileAsBlob = new Blob([textToWrite], {type:'text/plain'});
	var fileType = '';
    var fileNameToSaveAs = $('#filename').val();;

    var downloadLink = document.createElement("a");
    downloadLink.download = fileNameToSaveAs;
    downloadLink.innerHTML = "Download File";
    if (window.webkitURL != null)
    {
        // Chrome allows the link to be clicked
        // without actually adding it to the DOM.
        downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
    }
    else
    {
        // Firefox requires the link to be added to the DOM
        // before it can be clicked.
        downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
        downloadLink.onclick = destroyClickedElement;
        downloadLink.style.display = "none";
        document.body.appendChild(downloadLink);
    }

    downloadLink.click();
 });
 $('#input').on('paste', function(e) {
setTimeout(function(){ //break the callstack to let the event finish

    //alert($('#input').val()); //read the value of the input field   
$("#button").click();
$("#format").click();
  },0); 
 
   
});	
});
