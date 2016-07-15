$(document).ready(function () { 
   $('#EditResidence').dropdown();
   $('#progress').progress();
  $('#DeleteResidence').dropdown(); 
 
 
   $('.ui.form').form({ 
     fields: { 
       DeleteResidence: { 
         identifier: 'DeleteResidence', 
         rules: [{ 
           type: 'empty', 
           prompt: 'Select residence to delete', 
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
