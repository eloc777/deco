const fetchGetData = (url, headers = {}) => {
    //use built in fetch func to make a request to api
    return fetch(url, {
        method: 'GET',
        headers: headers,
    })
    //wait for server to respond
    .then(response => {
        if (!response.ok) {
            throw new Error('Server returned an error.');
        }
        //return response as js object we can use
        return response.json();
    })
    //log error message if something goes wrong
    .catch(error => {
        console.error('Error fetching data:', error);
        return null;
    });
};

export { fetchGetData };