let latlong = [];
var map;
const markers = [];

function initialize()
{
	retrieveMarkerLocations();
	
    var center =new google.maps.LatLng(53.347298,-6.268344);
    var initRadius = 10000;
    const mapProp = {
            center:center,
            zoom:7,
            mapTypeId:google.maps.MapTypeId.ROADMAP
    };
    
    
    var mapDiv = document.getElementById("googleMap");
    
    mapDiv.style.width = '100%';
    mapDiv.style.height = '500px';
    
      map = new google.maps.Map(mapDiv,mapProp);
   
      }

/**
 * Use ajax call to get users and their geolocations
 * pass returned array marker locations to callback method
 * Here is the format in which marker data stored
 * geoObj[0] is descripion.             
 * geoObj[1] is latitude                              
 * geoObj[2] is longitude  
 * We use geoObj[0] in the infoWindow. 
 * Click marker to reveal description.
 */
function retrieveMarkerLocations()
{
  $(function() {
    $.get("/Administrators/getCordinates", function(data) {
      $.each(data, function(index, geoObj) {
        console.log(geoObj[0] + " " + geoObj[1] + " " + geoObj[2] + " " + geoObj[3] + " " + geoObj[4]);
    });
      callback(data);
    });
  });
}

/**
 * we've got the marker location from data in ajax call
 * we now put data into an array
 * the format is 'firstName, xx.xxxx, yy.yyyyy' -> (firstName, lat, lng)
 * then invoke 'fitBounds' to render the markers, centre map and create infoWindow to display firstName
 */
function callback(data)
{

	  latlng = data; // store the array of data in a global for later use
	  fitBounds(latlng); // then invoke fitBounds to zoom and display markers
	  setInfoWindowListener(latlng);
}

/**
 * creates and positions markers
 * sets zoom so that all markers visible
 */
function fitBounds(latlngStr)
{
  const bounds = new google.maps.LatLngBounds();
    for (let i = 0; i < latlngStr.length; i++) 
    {
        marker = new google.maps.Marker({
            position: getLatLng(latlngStr[i]),
            map: map
        });
        markers[i] = marker;      
        bounds.extend(marker.position);
    }
    map.fitBounds(bounds);
    
}

function setInfoWindowListener(latlngStr)
{
    const infowindow = new google.maps.InfoWindow();
    
    for (let i = 0; i < latlng.length; i++) 
    {
      /*respond to click on marker by displaying infowindow text*/
      const marker = markers[i];
      google.maps.event.addListener(marker, 'click', (function (marker, i) {
          return function () {
            infowindow.setContent(latlngStr[i][3] + "<br />" + latlngStr[i][4]);
            infowindow.open(map, marker);
            
          }
      })(marker, i));
      
    }
}

/**
 * A helper function to convert the latlng string to individual numbers
 * and thence to a google.maps.LatLng object
 * @param str str is list of strings : username, lat, lon    
 * str[0] is description                
 * str[1] is latitude                              
 * str[2] is longitude                             
 * 
 * @param The object 'str' holding an individual marker data set
 * @return A google.maps.LatLng object containing the marker coordinates.
 */
function getLatLng(str)
{ 

  const lat = Number(str[1]);
  const lon = Number(str[2]);
  return new google.maps.LatLng(lat, lon);
}



google.maps.event.addDomListener(window, 'load', initialize);
