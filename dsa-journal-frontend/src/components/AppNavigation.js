import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import JournalEntryList from './JournalEntryList';
import AddJournalEntryForm from './AddJournalEntryForm';


const AppNavigation = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route index="/" element={ <JournalEntryList /> } />
                <Route path="/add-journal-entry" element={ <AddJournalEntryForm /> } />
            </Routes>
        </BrowserRouter>
    )
}

export default AppNavigation;