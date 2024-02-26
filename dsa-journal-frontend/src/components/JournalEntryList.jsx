import React, { useEffect, useState } from 'react';
import JournalEntryService from '../services/journal-app-service';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Pagination from 'react-bootstrap/Pagination';

const JournalEntryList = () => {
    const [entries, setEntries] = useState({});
    const [showModal, setShowModal] = useState({});
    const [currentPage, setCurrentPage] = useState(1);
    const [entriesPerPage] = useState(5);

    useEffect(() => {
        JournalEntryService.getJournalEntries()
            .then((response) => {
                console.log(response);
                setEntries(response.data);
                const initialModalState = {};
                Object.keys(response.data).forEach(filename => {
                    initialModalState[filename] = false;
                });
                setShowModal(initialModalState);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    
    const indexOfLastEntry = currentPage * entriesPerPage;
    const indexOfFirstEntry = indexOfLastEntry - entriesPerPage;
    const currentEntries = Object.entries(entries).slice(indexOfFirstEntry, indexOfLastEntry);

    const handleOpen = (filename) => () => {
        setShowModal({ ...showModal, [filename]: true });
    };

    const handleClose = (filename) => () => {
        setShowModal({ ...showModal, [filename]: false });
    };

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div>
            {currentEntries.map(([filename, content]) => (
                <div className='list-item' key={filename} onClick={handleOpen(filename)}>
                    <h2 className='list-text'>{filename.substring(0, filename.length - 4)}</h2>
                    <p className='list-text'>{content.substring(0, 200)}</p>
                    <Modal show={showModal[filename]} onHide={handleClose(filename)}>
                        <Modal.Header closeButton>
                            <Modal.Title>{filename}</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>{content}</Modal.Body>
                        <Modal.Footer>
                            <Button variant="secondary" onClick={handleClose(filename)}>
                                Close
                            </Button>
                        </Modal.Footer>
                    </Modal>
                </div>
            ))}
            {Object.keys(entries).length > entriesPerPage && (
                <Pagination>
                    {Array.from({ length: Math.ceil(Object.keys(entries).length / entriesPerPage) }, (_, i) => (
                        <Pagination.Item key={i} active={currentPage === i + 1} onClick={() => paginate(i + 1)} variant="dark">
                            {i + 1}
                        </Pagination.Item>
                    ))}
                </Pagination>
            )}
        </div>
    );
};

export default JournalEntryList;
