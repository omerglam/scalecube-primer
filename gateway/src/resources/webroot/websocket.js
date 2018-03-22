
var loc = window.location, new_uri;
if (loc.protocol === "https:") {
    new_uri = "wss:";
} else {
    new_uri = "ws:";
}
new_uri += "//" + loc.host;
new_uri += loc.pathname + "quotes";


var exampleSocket = new WebSocket(new_uri);

exampleSocket.onopen = function (event) {
};

window.onbeforeunload = function() {
	/* disable onclose handler first */
    websocket.onclose = function () {};
    websocket.close()
};

exampleSocket.onmessage = function (event) {
	   console.log(event.data);
//	  let rates = JSON.parse(event.data);
//	  if(rates.currencyTo === "BTC") {
//		  document.getElementById("btc-rate").innerText = rates.rate.toFixed(8);
//		  document.getElementById("usdapprox").innerText = (rates.rate *  document.getElementById("invest").value).toFixed(2);
//	  }
//	  else if (rates.currencyTo === "BTN"){
//	  	document.getElementById("btn-rate").innerText = rates.rate.toFixed(8);
//	  	document.getElementById("btnapprox").innerText =
//	  		(document.getElementById("invest").value *
//	  				(rates.rate / document.getElementById("btc-rate").innerText)).toFixed(8);
//	  }

};
