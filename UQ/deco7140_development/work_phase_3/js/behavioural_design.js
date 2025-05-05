//imports
import { initAccordion } from "./modules/accordion.js";
import { postFormData } from './modules/postFormData.js';
            
//event listeners
document.addEventListener('DOMContentLoaded', () => {
    //select the form by its id
    const form = document.getElementById('community-form');
    //selects a div or p by id to display any feedback message
    const feedback = document.getElementById('form-feedback');
    //adds event listener to run when the form is submitted
    form.addEventListener('submit', async (e) => {
        //prevents page from reloading (default)
        e.preventDefault();
        //updates feedback section
        feedback.textContent = 'Submitting...';
        //calls the module to submit form data
        const { success, data } = await postFormData(form, 'https://damp-castle-86239-1b70ee448fbd.herokuapp.com/decoapi/community/', {
            'student_number': 's4910011',
            'uqcloud_zone_id': '8a3d5aab',
        });
        //check for success and update message
        if (success) {
            feedback.textContent = data.message;
            form.reset();
        } else {
            feedback.textContent = data.message || 'Something went wrong.';
        }
    });
});
document.addEventListener("DOMContentLoaded", () => {
    initAccordion(".accordion");
    initAccordion(".accordion.nested");
}); 
