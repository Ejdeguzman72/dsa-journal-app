import Axios from 'axios';

const getJournalEntries = () => {
    return Axios.get('http://localhost:8088/retrieve-s3-objects')
}

export default {
    getJournalEntries
};