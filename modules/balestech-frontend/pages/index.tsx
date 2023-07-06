import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { DataGrid, GridColDef, GridSortModel, GridRowParams, GridRenderCellParams } from '@mui/x-data-grid';
import { TextField, Grid, Switch } from '@mui/material';
import config from '../config';
import { encontrarMaior, encontrarMenorMaiorQueZero } from '../util/utilB3';


interface StockItem {
  id: string;
  name: string;
  company: string;
  price: number;
  targetTrading: number;
  targetBTG: number;
  targetWsj: number;
  targetXp: number;
  volume: number;
  liquidez: string;
  valuation: string;
  targetDefinition: string;
}



const HomePage: React.FC = () => {
  const [data, setData] = useState<StockItem[]>([]);
  const [filteredData, setFilteredData] = useState<StockItem[]>([]);
  const [filter, setFilter] = useState<string>('');
  const [sortModel, setSortModel] = useState<GridSortModel>([]);
  const [checked, setChecked] = useState(false);

  const handleChange = (event) => {
    const isChecked = event.target.checked;
    setChecked(isChecked);

    if (isChecked) {
      // Aplica o filtro nas linhas com idade menor que 35
      //const filteredData = rows.filter((row) => row.age < 35);
      const filtered = data.filter((item) => {
        if(item.liquidez.includes("ALTA")){
          return true; 
        }
        if(item.liquidez.includes("MEDIA")){
          return true; 
        }
      }
      );
      setFilteredData(filtered);
    } 
  };

  const renderCellStyle = (params: GridRenderCellParams) => {
    const value            = params.value as number;
    const targetDefinition = params.row.targetDefinition as string;
    let style = <div>{value}</div>;
    if(targetDefinition == 'VENDA'){
      style = <div className='custom-cell-red'>{value}</div>;
    } else { 
      if(targetDefinition == 'COMPRA'){
        style = <div className='custom-cell-green'>{value}</div>;
      }
    }

    return style;
  }

  const renderCellMoneyStyle = (params: GridRenderCellParams) => {
    const formatter = new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' });
    const valorFormatado = formatter.format(params.value);
    return <div>{valorFormatado}</div>
  }
  
  const getRowClassName = (params: GridRowParams) => {
    return 'style-row';
  }

  const columns: GridColDef[] = [
    { field: 'name', headerName: 'Name', width: 75, headerClassName: 'header-row', headerAlign: 'center' },
    { field: 'company', headerName: 'Company', width: 250, headerClassName: 'header-row', headerAlign: 'center'  },
    { field: 'price', headerName: 'Price', width: 100, headerClassName: 'header-row', headerAlign: 'center' , renderCell: renderCellMoneyStyle },
    { field: 'targetTrading', headerName: 'TargetTrading', width: 100, headerClassName: 'header-row', headerAlign: 'center' , renderCell: renderCellMoneyStyle },
    { field: 'targetBTG', headerName: 'TargetBTG', width: 100, headerClassName: 'header-row', headerAlign: 'center' , renderCell: renderCellMoneyStyle },
    { field: 'targetWsj', headerName: 'TargetWsj', width: 100, headerClassName: 'header-row', headerAlign: 'center' , renderCell: renderCellMoneyStyle },
    { field: 'targetXp', headerName: 'TargetXp', width: 100, headerClassName: 'header-row', headerAlign: 'center' , renderCell: renderCellMoneyStyle },
    { field: 'volume', headerName: 'Volume', width: 150, headerClassName: 'header-row', headerAlign: 'center' , renderCell: renderCellMoneyStyle },
    { field: 'liquidez', headerName: 'Liquidez', width: 90, headerClassName: 'header-row', headerAlign: 'center'  },
    { field: 'valuation', headerName: 'Valuation', width: 90, headerClassName: 'header-row', headerAlign: 'center'  },
    { field: 'targetDefinition', headerName: 'TargetDefinition', width: 100, headerClassName: 'header-row', headerAlign: 'center' , renderCell: renderCellStyle },
  ];

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get<StockItem[]>(`${config.backendUrl}/stock/target`);
      console.log(response);
      setData(response.data);
      setFilteredData(response.data);
    } catch (error) {
      console.error('Erro ao buscar os dados do backend:', error);
    }
  };

  const handleFilterChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setFilter(event.target.value);
    filterData(event.target.value);
  };

  const filterData = (value: string) => {
    const filtered = data.filter((item) =>
      item.name.toLowerCase().includes(value.toLowerCase())
    );
    setFilteredData(filtered);
  };

  const handleSortChange = (model: GridSortModel) => {
    setSortModel(model);
  };
  

  
  return (
    <Grid container direction="column" alignItems="center" spacing={2}>
      <Grid item>
        <h1>BALESTech B3 - Suite</h1>
      </Grid>
      <Grid item>
        <TextField
          type="text"
          value={filter}
          onChange={handleFilterChange}
          placeholder="Filtrar por nome"
        />
        <Switch
          checked={checked}
          onChange={handleChange}
          inputProps={{ 'aria-label': 'controlled' }}
        />
      </Grid>
      <Grid item>
        <DataGrid
          rows={filteredData}
          columns={columns}
          sortModel={sortModel}
          onSortModelChange={handleSortChange}
          getRowClassName={getRowClassName}
        />
      </Grid>
    </Grid>
  );
};

export default HomePage;
