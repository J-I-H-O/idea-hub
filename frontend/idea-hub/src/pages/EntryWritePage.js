import React from "react";
import Responsive from "../components/common/Responsive";
import TopSpace from "../components/common/TopSpace";
import EntryEditorContainer from "../containers/entryWrite/EntryEditorContainer";
import EntryWriteActionButtonsContainer from "../containers/entryWrite/EntryWriteActionButtonsContainer";
import styled from "styled-components";
import BottomInfo from "../components/common/BottomInfo";
import HeaderWithHamburger from "../components/common/HeaderWithHamburger";
import ToTopButton from "../components/common/ToTopButton";

const Spacer = styled.div`
  height: 2rem;
`;

const EntryWritePage = () => {
  return (
    <>
      <HeaderWithHamburger />
      <Spacer />
      <TopSpace title="작품 등록" description="등록할 작품 정보 입력" />
      <Responsive>
        <EntryEditorContainer />
        <EntryWriteActionButtonsContainer />
      </Responsive>
      <ToTopButton />
      <BottomInfo />
    </>
  );
};

export default EntryWritePage;
