/*
 * ASCII Camera
 * http://idevelop.github.com/ascii-camera/
 *
 * Copyright 2013, Andrei Gheorghe (http://github.com/idevelop)
 * Released under the MIT license
 */

(function() {
	var asciiContainer = document.getElementById("ascii");
	var capturing = false;

	camera.init({
		width: 160,
		height: 120,
		fps: 30,
		mirror: false,

		onFrame: function(canvas) {
			ascii.fromCanvas(canvas, {
				// contrast: 128,
				callback: function(asciiString) {
					asciiContainer.innerHTML = asciiString;
				}
			});
		},

		onSuccess: function() {
			document.getElementById("info").style.display = "none";

			capturing = true;
			document.getElementById("pause").style.display = "block";
			document.getElementById("pause").onclick = function() {
				if (capturing) {
					camera.pause();
				} else {
					camera.start();
				}
				capturing = !capturing;
			};
		},

		onError: function(error) {
			// TODO: log error
		},

		onNotSupported: function() {
			document.getElementById("info").style.display = "none";
			asciiContainer.style.display = "none";
			document.getElementById("notSupported").style.display = "block";
		}
	});
})();