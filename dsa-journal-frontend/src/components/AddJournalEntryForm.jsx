import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import JournalService from '../services/journal-app-service';

const AddJournalEntryForm = () => {
    const initialState = {
        content: ""
    }
    const [content, setContent] = useState(initialState);
    const [submitted, setSubmitted] = useState(false)

    const handleContentChange = (e) => {
        setContent(e.target.value);
    }
    const submitJournalEntry = () => {
        let data = {
            content: content
        }

        JournalService.addJournalEntry(data)
            .then((response) => {
                console.log(response.data);
                setSubmitted(true);
            })
            .catch((error) => {
                console.log('Error: ', error);
            })
    }

    return (
        <div>
            <form>
                <textarea placeholder='Enter entry here...' rows="30" cols="150" onChange={handleContentChange}></textarea>
                <br></br>
                <Button className="button" variant="dark" onClick={submitJournalEntry}>Submit</Button>
            </form>
        </div>
    )
}

export default AddJournalEntryForm;