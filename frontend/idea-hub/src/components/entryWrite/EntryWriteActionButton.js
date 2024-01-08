import React from "react";
import styled from "styled-components";
import Button from "../common/Button";

const WriteActionButtonsBlock = styled.div`
  margin-top: 5rem;
  margin-bottom: 5rem;
  margin-left: 1.25rem;
  button + button {
    margin-left: 0.5rem;
  }
`;

const EntryWriteActionButtons = ({ onCancel, onPublish, isEdit }) => {
  return (
    <WriteActionButtonsBlock>
      <Button
        onClick={onPublish}
        style={{
          width: "10rem",
          height: "3rem",
          backgroundColor: "black",
          color: "white",
        }}
      >
        {isEdit ? "수정" : "등록"}
      </Button>
      <Button onClick={onCancel} style={{ width: "10rem", height: "3rem" }}>
        취소
      </Button>
    </WriteActionButtonsBlock>
  );
};

export default EntryWriteActionButtons;
