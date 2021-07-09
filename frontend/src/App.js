import LandingPage from "./pages/LandingPage";
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import CardSearchPage from "./pages/CardSearchPage";
import CardCollectionPage from "./pages/CardCollectionPage";
import SingleCardPage from "./pages/SingleCardPage";
import MagicCrowHeader from "./components/MagicCrowHeader";
import MagicCrowFooter from "./components/MagicCrowFooter";
import SingleCardInCollectionPage from "./pages/SingleCardInCollectionPage";
import LoginPage from "./pages/LoginPage";
import AuthProvider from "./context/AuthProvider";
import PrivateRoute from "./routing/PrivateRoute";

function App() {


    return (

        <Router>
            <AuthProvider>
                <MagicCrowHeader/>
                <Switch>
                    <Route path={'/'} exact>
                        <LoginPage/>
                    </Route>
                    <PrivateRoute path={'/home'} exact>
                        <LandingPage/>
                    </PrivateRoute>
                    <PrivateRoute path={'/searchCards'} exact>
                        <CardSearchPage/>
                    </PrivateRoute>
                    <PrivateRoute path={'/myCollection'} exact>
                        <CardCollectionPage/>
                    </PrivateRoute>
                    <PrivateRoute path={'/:id'} exact>
                        <SingleCardPage/>
                    </PrivateRoute>
                    <PrivateRoute path={'/myCollection/:id'} exact>
                        <SingleCardInCollectionPage/>
                    </PrivateRoute>
                </Switch>
                <MagicCrowFooter/>
            </AuthProvider>
        </Router>

    )
}

export default App;
