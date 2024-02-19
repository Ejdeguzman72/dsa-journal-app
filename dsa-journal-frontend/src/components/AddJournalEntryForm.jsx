import React from 'react';
import Button from 'react-bootstrap/Button';

const AddJournalEntryForm = () => {
    return (
        <div>
            <form>
                <textarea placeholder='Enter entry here...' rows="20" cols="100"></textarea>
                <br></br>
                <Button className="button" variant="dark">Submit</Button>
            </form>
        </div>
    )
}

export default AddJournalEntryForm;