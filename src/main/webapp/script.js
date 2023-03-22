'use strict';

document.addEventListener('DOMContentLoaded', () => {
			const createButton = document.querySelector("#create"),
					navigation = document.querySelector("nav");

			createButton.addEventListener("click", () => {
				navigation.innerHTML = `
   				<p>Wait until a detail is created.</p>
   				<p>You will redirect to a page with information about the detail.</p>
   			`;
			});
});