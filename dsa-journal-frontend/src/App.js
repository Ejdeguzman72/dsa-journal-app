import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavbarMenu from './components/NavbarMenu';
import AppNavigation from './components/AppNavigation';

function App() {
  return (
    <div>
      <div className="background">
        <div className="container">
          <div className="textarea-content">
            <NavbarMenu />
            <AppNavigation />
          </div>
        </div>
      </div>
    </div>

  );
}

export default App;
