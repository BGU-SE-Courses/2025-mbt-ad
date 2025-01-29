// @provengo summon ctrl



/** This is the block of code for testing provengo and tests and not generating the test suits

 * List of events "of interest" that we want test suites to cover.
*/
const EVENTS_OF_INTEREST = [ //mostly used for test testing and not system testing
    "new session - Admin",
    "AdminLogIn",
    "OpenCustomersList",
    "FirstUserDeactivated",
    "DeactivationValidation",
    "close session - Admin",
    "new session - User",
    "UserLogIn",
    "EditAccount",
    "close session - User"
];

const adminEvents = [
    "new session - Admin",
    "AdminLogIn",
    "OpenCustomersList",

    "FirstUserDeactivated",
    "DeactivationValidation",
    "ReActivateUserForSetUpNextTestNextTest",
    "close session - Admin"
];

const userEvents = [
    "new session - User",
    "UserLogIn",

    "EditAccount",
    "close session - User"
];
 // Store marked events in a dictionary for consistency
 const MARKED_EVENTS = EVENTS_OF_INTEREST.reduce((acc, event) => {
 acc[event] = Ctrl.markEvent(event);
 return acc;
 }, {});


 //const GOALS = Object.values(MARKED_EVENTS);




/**
 * Generates pairs of events between user and admin events in both orders.
 * @returns {Array} A list of event pairs in each possible order (admin event first and reversed).
 * Only events which can happen at different orders are here.
 */
function generateEventPairs() {
    const pairs = [];
    const selectedAdminEvents = [
        "new session - Admin",
        "AdminLogIn",
        "OpenCustomersList"
    ];
    const selectedUserEvents = [
        "new session - User",
        "UserLogIn",
        "EditAccount",
        "close session - User"
    ];

    selectedUserEvents.forEach(userEvent => {
        selectedAdminEvents.forEach(adminEvent => {
            pairs.push([userEvent, adminEvent]); // User event first
            pairs.push([adminEvent, userEvent]); // Admin event first
        });
    });

    return pairs;
}





// Create pairs using the marked events
function generateMarkedEventPairs() {
    const pairs = generateEventPairs();
    return pairs.map(([event1, event2]) => [MARKED_EVENTS[event1], MARKED_EVENTS[event2]]);
}

const MARKED_EVENT_PAIRS = generateMarkedEventPairs();




/**
 * Ranks test suites by how many pairs of events from the MARKED_EVENT_PAIRS are in test, in order.
 * Is will work for any marked event pairs so its generalized. It is used both for domain and pairwise ensemble
 * The more goals are met, the higher the score.
 *
 * It makes no difference if a goal was met more than once.
 *
 * @param {Event[][]} ensemble The test suite to be ranked.
 * @returns Number of events from GOALS that have been met.
 */
function rankByMetGoals( ensemble ) {
    const unreachedGoals = [];
    for ( let idx=0; idx<MARKED_EVENT_PAIRS.length; idx++ ) {
        unreachedGoals.push(MARKED_EVENT_PAIRS[idx]);
    }
    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
        let test = ensemble[testIdx];

        // Loop through each pair in MARKED_EVENT_PAIRS
        for (let ugIdx = unreachedGoals.length - 1; ugIdx >= 0; ugIdx--) {
            let [event1, event2] = unreachedGoals[ugIdx];

            let firstFound = false;

            // Check if both events appear in order within the test
            for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
                let event = test[eventIdx];

                // Look for the first event
                if (!firstFound && event.contains(event1)) {
                    firstFound = true;
                }

                // If the first event was found, check for the second event
                if (firstFound && event.contains(event2)) {
                    unreachedGoals.splice(ugIdx, 1); // Goal met, remove it
                    break; // Exit loop once the pair is found
                }
            }
        }
    }

    // Return how many pairs were met (i.e., how many were removed from unreachedGoals)
    return MARKED_EVENT_PAIRS.length - unreachedGoals.length;
}






// /**       this is a base rankmetbygoal for test creation porpuses
//  * Ranks test suites by how many events from the GOALS array were met.
//  * The more goals are met, the higher the score.
//  *
//  * It make no difference if a goal was met more then once.
//  *
//  * @param {Event[][]} ensemble The test suite to be ranked.
//  * @returns Number of events from GOALS that have been met.
//  * */
// function arankByMetGoals( ensemble ) {
//     const unreachedGoals = [];
//     for ( let idx=0; idx<GOALS.length; idx++ ) {
//         unreachedGoals.push(GOALS[idx]);
//     }
//
//     for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
//         let test = ensemble[testIdx];
//         for (let ugIdx=unreachedGoals.length-1; ugIdx >=0; ugIdx--){
//             let unreachedGoal = unreachedGoals[ugIdx];
//             for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
//                 let event = test[eventIdx];
//                 if (unreachedGoal.contains(event)) {
//                     unreachedGoals.splice(ugIdx, 1);
//                 }
//             }
//         }
//     }
//
//     return GOALS.length-unreachedGoals.length;
// }

/**
 * Ranks potential test suites based on the percentage of goals they cover.
 * Goal events are defined in the GOALS array above. An ensemble with rank
 * 100 covers all the goal events.
 *
 * Multiple ranking functions are supported - to change ranking function,
 * use the `ensemble.ranking-function` configuration key, or the
 * --ranking-function <functionName> command-line parameter.
 *
 * @param {Event[][]} ensemble the test suite/ensemble to be ranked
 * @returns the percentage of goals covered by `ensemble`.
 */
function rankingFunction(ensemble) {

    // How many goals did `ensemble` hit?
    const metGoalsCount = rankByMetGoals(ensemble);
    // What percentage of the goals did `ensemble` cover?
    const metGoalsPercent = metGoalsCount/MARKED_EVENT_PAIRS.length;

    return metGoalsPercent * 100; // convert to human-readable percentage
}

