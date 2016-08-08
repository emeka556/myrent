let Array = [];

(function()
{
  $(function() {
    $.get("/AdminReport/getPercents", function(data) {
      $.each(data, function(index, geoObj) {
        console.log(geoObj[0] + " " + geoObj[1]);
    });
      callback(data); 
    });
  });
}());

function Piechart () {
	
	var Piechart = new CanvasJS.Chart("chartContainer",
	{
		theme : "theme4",
		title:{
			text: "Landlord rent rolls as % income all portfolios"
		},
                animationEnabled: true,
		legend:{
			verticalAlign: "center",
			horizontalAlign: "right",
			fontSize: 12,
			fontFamily: "Times New Roman"
				
		},
		
		data: [
		{        
			type: "pie",       
			indexLabelFontFamily: "Times New Roman",       
			indexLabelFontSize: 12,
			
			showInLegend: true,
			startAngle: 0,      
			toolTipContent:"{legendText} - {y}%",
			dataPoints: Array
				
				
		}
		]
	});
	Piechart.render();
}

function callback(data)
{
	
	
	for(let i = 0; i < data.length; i++)
		{
	  Array[i] = {  y: data[i][0], legendText: data[i][1], label: data[i][0] + " %"};
	  console.log('y: ' + data[i][0] + " " + 'legendText: ' +  data[i][1] + " " );
	  Piechart();
		}
}