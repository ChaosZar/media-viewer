import React, {useEffect, useState} from 'react';
import {PictureService} from "../PictureService";
import {Picture} from "../Picture";

export const PictureList = () => {
    const [pictures, setPictures] = useState([] as Picture[]);
    const pictureService = new PictureService();

    useEffect(() => {
        pictureService.getPictures().then(data => setPictures(data));
    }, []);

    return (
        <div>
            <div className="card">
                <div className="p-grid">

                </div>
            </div>
        </div>
    );
}
       