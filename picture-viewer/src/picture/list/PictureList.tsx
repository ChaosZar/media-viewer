import React, {useEffect, useState} from 'react';
import {PictureService} from "../PictureService";
import {Picture} from "../Picture";
import {PictureDirectory} from "../PictureDirectory";
import {Card} from "primereact/card";
import "./PictureList.css";
import {Dialog} from "primereact/dialog";

type PictureProps = {
    dir: PictureDirectory
}

export const PictureList = (props: PictureProps) => {
    const [pictures, setPictures] = useState([] as Picture[]);
    const [picture, setPicture] = useState(new Picture());
    const [showPicture, setShowPicture] = useState(false);
    const pictureService = new PictureService();
    const dir = props.dir;

    useEffect(() => {
        pictureService.getPictures(dir).then(data => {
            setPictures(data)
        });
    }, []);

    function showDialog(picture: Picture) {
        setPicture(picture);
        setShowPicture(true);
    }

    function getMediaThumb(pic: Picture) {
        if (pic.mediatype?.startsWith("video")) {
            return <video style={{maxHeight: "100%", maxWidth: "100%", height: "auto", width: "auto"}}
                          playsInline={false} controls={false} autoPlay={false}
                          src={getSrc(pic)}/>;
        } else {
            return <img style={{maxHeight: "100%", maxWidth: "100%", height: "auto", width: "auto"}}
                        alt={dir.path}
                        src={getSrc(pic)}/>;
        }
    }

    function renderPicture(pic: Picture) {
        return <Card className="hoverable p-text-center">
            <div style={{width: 200, height: 200}} onClick={() => showDialog(pic)}>
                {getMediaThumb(pic)}
            </div>
            <div style={{maxWidth: 200}} className="p-text-center">{pic.name}</div>
        </Card>
    }

    function getSrc(picture: Picture) {
        return `${process.env.REACT_APP_MEDIA_SERVER_URL}` + dir.path + "/" + picture.name;
    }

    function renderDialogContent(picture: Picture) {
        if (picture.mediatype?.startsWith("video")) {
            return <video src={getSrc(picture)} controls={true}/>
        } else {
            return <img alt={picture.name} src={getSrc(picture)}/>
        }
    }

    return (
        <div>
            <div className="card">
                <div className="p-grid">
                    {pictures.map(p => {
                        return renderPicture(p);
                    })}
                </div>
            </div>
            <Dialog onHide={() => setShowPicture(false)}
                    visible={showPicture} header={picture.name} modal={true} draggable={false} resizable={false}>
                {renderDialogContent(picture)}
            </Dialog>

        </div>
    );
}
       