// The Cloud Functions for Firebase SDK to create Cloud Functions and setup triggers.
const functions = require('firebase-functions');

// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);
//admin.initializeApp();

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
exports.getQuestions = functions.https.onRequest((request, response) => {
    var count = request.query.count || "0";
    var result = "Result : ";

    try {
        const db = admin.firestore();
        // .doc('CZYeaUSKMYSnxL1YUmLP');

        db.collection('IT Questions').get()
            .then(docs => {
                if (!docs.exists) {
                    result += 'No such document!';
                } else {
                    result += 'Document data:' + docs.data();
                }
                response.send(result);

                return Promise.resolve("Finished")
            }).catch(() => {
                response.send(result);
                return Promise.resolve("Finished")
            });

    } catch (err) {
        result += "ERROR2 : " + err;
    }

    // response.send(result);

});


// Function URL: https://us-central1-misurata-university-guide.cloudfunctions.net/getQuestions