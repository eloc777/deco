/**
 * IMPORTS
 * Keep track of external modules being used
 * */
import { initAccordion } from "./modules/accordion.js";
import { fetchGetData } from "./modules/getData.js";

/**
 * CONSTANTS
 * Define values that don't change e.g. page titles, URLs, etc.
 * */

/**
 * VARIABLES
 * Define values that will change e.g. user inputs, counters, etc.
 * */

/**
 * FUNCTIONS
 * Group code into functions to make it reusable
 * */

/**
 * EVENT LISTENERS
 * The code that runs when a user interacts with the page
 * */

document.addEventListener("DOMContentLoaded", () => {
    initAccordion(".accordion");
    initAccordion(".accordion.nested");
    const container = document.getElementById("community-list");

    fetchGetData(
        "https://damp-castle-86239-1b70ee448fbd.herokuapp.com/decoapi/community/",
        {
            student_number: "s4910011",
            uqcloud_zone_id: "8a3d5aab",
        }
    ).then((data) => {
        if (!data) {
            container.innerHTML =
                '<p class="text-danger">Unable to load community members.</p>';
            return;
        }

        data.forEach((member) => {
            const card = document.createElement("div");
            card.className = "card mb-3";
            card.innerHTML = `
            <div class="card-body">
            <h5 class="card-title">${member.name}</h5>
            <p class="card-text">${member.message || "No message provided."}</p>
${member.photo ? `<img class="user-photo" src="${member.photo}" alt="This member's user photo" />` : ''}            </div>
        `;
            container.appendChild(card);
        });
    });
});
