import React from "react";
import ReactDOM from "react-dom/client";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";
import createSagaMiddleware from "redux-saga";
import { createStore, applyMiddleware } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import rootReducer, { rootSaga } from "./modules";
import * as serviceWorker from "./serviceWorker";
import "./index.css";
import App from "./App";

const sagaMiddleware = createSagaMiddleware();
const store = createStore(
  rootReducer,
  // composeWithDevTools(applyMiddleware(sagaMiddleware))
  applyMiddleware(sagaMiddleware)
);

function loadUser() {
  try {
    const user = localStorage.getItem("user");
    //do nothing if user info is not in local storage.
    if (!user) return;

    // //put user info into redux store if user info is in local storage.
    // store.dispatch(tempSetUser(user));
    // //check if user logged in
    // store.dispatch(check());
  } catch (e) {
    console.log(e);
  }
}

sagaMiddleware.run(rootSaga);
// loadUser();

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <Provider store={store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>
);

serviceWorker.unregister();
