import Axios from 'axios';

const getJournalEntries = () => {
    return Axios.get('http://ec2-3-87-35-240.compute-1.amazonaws.com:8088/retrieve-s3-objects');
}

const addJournalEntry = (data) => {
    return Axios.post(`http://ec2-3-87-35-240.compute-1.amazonaws.com:8088/upload-entry`, data);
}

export default {
    getJournalEntries,
    addJournalEntry
};