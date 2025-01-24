// @provengo summon ctrl

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



const GOALS = EVENTS_OF_INTEREST.map(event => Ctrl.markEvent(event));

const makeGoals = function(){
    const pairs = [];
    for (let i = 0; i < EVENTS_OF_INTEREST.length; i++) {
        for (let j = i + 1; j < EVENTS_OF_INTEREST.length; j++) {
            pairs.push([Ctrl.markEvent(EVENTS_OF_INTEREST[i]), Ctrl.markEvent(EVENTS_OF_INTEREST[j])]);
        }
    }
    return pairs;
}

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
    const unreachedGoals = makeGoals();
    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
        const test = ensemble[testIdx];
        for (let ugIdx = unreachedGoals.length - 1; ugIdx >= 0; ugIdx--) {
            const [goal1, goal2] = unreachedGoals[ugIdx];
            if (test.includes(goal1) && test.includes(goal2)) {
                unreachedGoals.splice(ugIdx, 1);
            }
        }
    }
    return makeGoals().length - unreachedGoals.length;

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
    const metGoalsCount = rankByMetGoals(ensemble);
    const totalGoals = makeGoals().length;
    return (metGoalsCount / totalGoals) * 100; // Human-readable percentage
}

// Constraints to block specific event sequences
bthread("Constraint1", function () {
    sync({ waitFor: Event("EditAccount"), block: Event("FirstUserDeactivated") });
});
