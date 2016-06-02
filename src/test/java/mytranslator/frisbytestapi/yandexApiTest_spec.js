/**
 * frisby test for Yandex API
 */


var frisby = require('frisby');

frisby.create('getting language list')
    .get('https://translate.yandex.net/api/v1.5/tr/getLangs?' +
    'key=trnsl.1.1.20160311T110648Z.2843309257351b77.503eb0ab4fee6d8e09936972b4bc73810e4b12b4' +
    '&ui=en')
    .expectStatus(200)
    .expectHeader('Content-Type', 'application/json')
    .expectJSON({ })



    .toss();