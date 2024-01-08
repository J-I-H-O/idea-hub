import React from "react";
import HeaderWithHamburger from "../components/common/HeaderWithHamburger";
import MainSlideShow from "../components/main/MainSlideShow";
import MainSubMenu from "../components/main/MainSubMenu";
import ToTopButton from "../components/common/ToTopButton";
import BottomInfo from "../components/common/BottomInfo";

const MainPage = () => {
  return (
    <div>
      <HeaderWithHamburger />
      <MainSlideShow />
      <MainSubMenu />
      <ToTopButton />
      <BottomInfo />
    </div>
  );
};

export default MainPage;
