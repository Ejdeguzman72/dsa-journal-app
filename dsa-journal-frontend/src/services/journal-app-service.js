import Axios from 'axios';

const getJournalEntries = () => {
    return Axios.get('http://localhost:8088/retrieve-s3-objects');
}

const addJournalEntry = (data) => {
    return Axios.post(`http://localhost:8088/upload-entry`, data);
}

export default {
    getJournalEntries,
    addJournalEntry
};