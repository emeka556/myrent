$(document).ready(function () { 
   $('#EditResidence').dropdown();
   
  $('#DeleteResidence').dropdown(); 
 
 
   $('.ui.form').form({ 
     fields: { 
    	 DeleteResidence: { 
         identifier: 'eircode', 
         rules: [{ 
           type: 'empty', 
           prompt: 'Please Select from the dropdown', 
         },], 
       }, 
       
 

       EditResidence: { 
         identifier: 'eircode1', 
         rules: [{ 
           type: 'empty', 
           prompt: 'Select residence to edit ', 
         },], 
       }, 
     }, 
   }); 
 }); 