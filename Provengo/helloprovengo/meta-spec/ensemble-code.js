// @provengo summon ctrl

/**
 * List of events "of interest" that we want test suites to cover.
 */
const EVENTS_OF_INTEREST = [
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

const GOALS = Object.values(MARKED_EVENTS);

const makePairWiseGoals = function () {
    return GOALS.map(event => [event]); // Single-event goals
};

/**
 * Ranks test suites by how many events from the GOALS array were met.
 * The more goals are met, the higher the score.
 *
 * It make no difference if a goal was met more then once.
 *
 * @param {Event[][]} ensemble The test suite to be ranked.
 * @returns Number of events from GOALS that have been met.
 */
function rankByMetGoals( ensemble ) {
    const unreachedGoals = [];
    for ( let idx=0; idx<GOALS.length; idx++ ) {
        unreachedGoals.push(GOALS[idx]);
    }

    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
        let test = ensemble[testIdx];
        for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
            let event = test[eventIdx];
            for (let ugIdx=unreachedGoals.length-1; ugIdx >=0; ugIdx--) {
                let unreachedGoal = unreachedGoals[ugIdx];
                if ( unreachedGoal.contains(event) ) {
                    unreachedGoals.splice(ugIdx,1);
                }
            }
        }
    }

    return GOALS.length-unreachedGoals.length;
}

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
    const metGoalsPercent = metGoalsCount/GOALS.length;

    return metGoalsPercent * 100; // convert to human-readable percentage
}

