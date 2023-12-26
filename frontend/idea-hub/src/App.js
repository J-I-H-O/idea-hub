import { Route } from "react-router-dom";
import EntryWritePage from "./pages/EntryWritePage";

const App = () => {
  return (
    <>
      <Route component={EntryWritePage} path="/entrywrite" />
    </>
  );
};

export default App;
