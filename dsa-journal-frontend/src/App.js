import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavbarMenu from './components/NavbarMenu';
import JournalEntryList from './components/JournalEntryList';

function App() {
  return (
    <div>
      <NavbarMenu />
      <div className="background">
        <div className="container">
          <div className="textarea-content">
            <JournalEntryList />
          </div>
        </div>
      </div>
    </div>

  );
}

export default App;
