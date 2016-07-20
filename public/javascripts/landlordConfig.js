$(document).ready(function () { 
   $('#EditResidence').dropdown();
   
  $('#DeleteResidence').dropdown(); 
 
 
   $('.ui.form').form({ 
     fields: { 
    	 eircode: { 
         identifier: 'eircode', 
         rules: [{ 
           type: 'empty', 
           prompt: 'Please Select from the dropdown residence to delete', 
         },], 
       }, 
 

       EditResidence: { 
         identifier: 'EditResidence', 
         rules: [{ 
           type: 'empty', 
           prompt: 'Select residence to edit ', 
         },], 
       }, 
     }, 
   }); 
 }); 
