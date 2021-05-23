import React, {useEffect, useState} from 'react';
import {PictureDirectory} from "../PictureDirectory";
import {PictureDirectoryService} from "../PictureDirectoryService";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {Dialog} from "primereact/dialog";
import {PictureList} from "./PictureList";

export const DirectoryList = () => {
    const [dirs, setDirs] = useState([] as PictureDirectory[]);
    const [showDir, setShowDir] = useState({} as PictureDirectory);
    const [showDialog, setDialogVisible] = useState(false);
    const pictureDirectoryService = new PictureDirectoryService();

    useEffect(() => {
        pictureDirectoryService.getDirectories().then(data => {
            setDirs(data)
        });
    }, []);

    function onShow(dir: PictureDirectory) {
        setShowDir(dir);
        setDialogVisible(true);
    }

    return (
        <div>
            <div className="card">
                <DataTable value={dirs} onRowClick={(ev) => onShow(ev.data)} selectionMode={"single"}>
                    <Column sortable={true} field="path" header="Name"/>
                </DataTable>
            </div>

            <Dialog onHide={() => setDialogVisible(false)}
                    header={showDir.path} resizable={false} modal={true} draggable={false}
                    visible={showDialog} maximized={true}>
                <PictureList dir={showDir}/>
            </Dialog>
        </div>
    );
}
